import asyncio
import aiohttp
import argparse
import csv
import json
from bs4 import BeautifulSoup
from typing import List, Dict, Optional, Any


class AsyncCurlDataExtractor:
    """
    Асинхронный класс для извлечения данных с помощью cURL-подобных запросов
    и экспорта в CSV или JSON.
    """

    BASE_URL = "https://www.vbr.ru/api/product/tab/"
    PRODUCT_TYPE = "CreditCard"
    IS_CALC_BTN_TARGET_BLANK = "False"
    TYPES = ["GeneralTerms", "RequirementsDocuments", "CashWithdrawal", "ProofOfIncome", "CardCashback"]

    def __init__(self, product_ids: List[str], output_file: str, output_format: str):
        """
        Инициализирует экстрактор данных.
        """
        self.product_ids = product_ids
        self.output_file = output_file
        self.output_format = output_format
        if output_format not in ['csv', 'json']:
            raise ValueError("output_format must be 'csv' or 'json'")

    async def _make_request(self, session: aiohttp.ClientSession, product_id: str, type_: str) -> Optional[str]:
        """
        Выполняет асинхронный запрос к API.
        """
        params = {
            "productType": self.PRODUCT_TYPE,
            "productId": product_id,
            "type": type_,
            "isCalcBtnTargetBlank": self.IS_CALC_BTN_TARGET_BLANK,
        }
        try:
            async with session.get(self.BASE_URL, params=params, timeout=10) as response:
                response.raise_for_status()
                return await response.text()
        except (aiohttp.ClientError, asyncio.TimeoutError) as e:
            print(f"Ошибка при запросе для product_id={product_id}, type={type_}: {e}")
            return None

    def _parse_general_terms(self, html_content: str) -> Dict[str, str]:
        """Парсинг GeneralTerms."""
        if not html_content:
            return {}
        soup = BeautifulSoup(html_content, 'html.parser')
        data = {}
        ul = soup.find('ul', class_='product-card-conditions')
        if ul:
            for li in ul.find_all('li'):
                # Используем .stripped_strings для получения списка всех строк внутри тега
                parts = list(li.stripped_strings)
                if len(parts) >= 2:  # Убеждаемся, что есть и ключ, и значение
                    key = parts[0].replace(":", "").strip() # Убираем двоеточие
                    value = " ".join(parts[1:]).strip()  # Объединяем все последующие строки
                    data[key] = value
        return data

    def _parse_requirements_documents(self, html_content: str) -> Dict[str, str]:
        """Парсинг RequirementsDocuments."""
        if not html_content:
            return {}
        soup = BeautifulSoup(html_content, 'html.parser')
        data = {}
        ul = soup.find('ul', class_='product-card-conditions')
        if ul:
             for li in ul.find_all('li'):
                parts = list(li.stripped_strings)
                if len(parts) >= 2:
                    key = parts[0].replace(":", "").strip()
                    value = " ".join(parts[1:]).strip()
                    data[key] = value
        return data


    def _parse_cash_withdrawal(self, html_content: str) -> Dict[str, Any]:
        """Парсинг CashWithdrawal."""
        if not html_content:
            return {}

        soup = BeautifulSoup(html_content, 'html.parser')
        data: Dict[str, Any] = {}

        # Находим все блоки с информацией о снятии наличных
        sections = soup.find_all('div', class_='m-bottom-small')
        for section in sections:
            header_div = section.find('b')  # Находим заголовок секции (<b>)
            if not header_div:
                continue
            header = header_div.get_text(strip=True)
            data[header] = []  # Создаем список для каждой секции

            # Находим таблицу, следующую за заголовком
            table = section.find_next_sibling('div', class_='product-card-table-overflow')
            if not table:
                continue
            table_body = table.find('table')
            if not table_body:
                continue

            # Получаем заголовки таблицы
            headers = [th.get_text(strip=True) for th in table_body.find_all('th')]

            # Парсим строки таблицы
            for row in table_body.find_all('tr'):
                row_data = {}
                cells = row.find_all('td')
                if not cells or len(cells) != len(headers):  # Пропускаем строку заголовков
                    continue
                for i, cell in enumerate(cells):
                    row_data[headers[i]] = cell.get_text(strip=True)
                data[header].append(row_data)

        return data

    def _parse_proof_of_income(self, html_content: str) -> Dict[str, str]:
        """Парсинг ProofOfIncome."""
        if not html_content:
            return {}
        soup = BeautifulSoup(html_content, 'html.parser')
        data = {}
        ul = soup.find('ul', class_='product-card-conditions')

        if ul:
            for li in ul.find_all('li'):
                parts = list(li.stripped_strings)
                if len(parts) >= 2:
                    key = parts[0].replace(":", "").strip()
                    value = " ".join(parts[1:]).strip()
                    data[key] = value
        return data

    def _parse_card_cashback(self, html_content: str) -> Dict[str, str]:
        """Парсинг CardCashback."""
        if not html_content:
            return {}
        soup = BeautifulSoup(html_content, 'html.parser')
        data = {}
        ul = soup.find('ul', class_='product-card-conditions')
        if ul:
            for li in ul.find_all('li'):
                parts = list(li.stripped_strings)
                if len(parts) >= 2:
                    key = parts[0].replace(":", "").strip()
                    value = " ".join(parts[1:]).strip()
                    data[key] = value
        return data
    
    def _parse_html(self, html_content: str, type_: str) -> Dict[str, Any]:
      """
      Извлекает данные из HTML-ответа, используя соответствующий парсер.
      """
      if type_ == "GeneralTerms":
          return self._parse_general_terms(html_content)
      elif type_ == "RequirementsDocuments":
          return self._parse_requirements_documents(html_content)
      elif type_ == "CashWithdrawal":
          return self._parse_cash_withdrawal(html_content)
      elif type_ == "ProofOfIncome":
          return self._parse_proof_of_income(html_content)
      elif type_ == "CardCashback":
          return self._parse_card_cashback(html_content)
      else:
          return {}

    async def _extract_data_for_product(self, session: aiohttp.ClientSession, product_id: str) -> Dict[str, Any]:
      """
      Асинхронно извлекает и структурирует данные для одного продукта.
      """
      product_data: Dict[str, Any] = {"product_id": product_id}
      tasks = [self._make_request(session, product_id, type_) for type_ in self.TYPES]
      results = await asyncio.gather(*tasks)

      combined_data: Dict[str, Any] = {}  # Словарь для сбора всех данных

      for i, type_ in enumerate(self.TYPES):
          html = results[i]
          parsed_section = self._parse_html(html, type_)
          # Объединяем данные, а не перезаписываем
          for key, value in parsed_section.items():
              combined_data[key] = value
      product_data.update(combined_data)
      return product_data
    
    async def _extract_all_data(self) -> List[Dict[str, Any]]:
        """
        Асинхронно извлекает данные для всех продуктов.
        """
        async with aiohttp.ClientSession() as session:
            tasks = [self._extract_data_for_product(session, product_id) for product_id in self.product_ids]
            all_data = await asyncio.gather(*tasks)
            return all_data

    def _write_to_csv(self, data: List[Dict[str, Any]]):
      """Записывает данные в CSV, обрабатывая вложенные словари и списки."""
      if not data:
          print("Нет данных для записи.")
          return

      with open(self.output_file, 'w', newline='', encoding='utf-8') as csvfile:
          # Собираем все возможные ключи (колонки)
          fieldnames = ['product_id']
          for item in data:
              for key in item.keys():
                  if key != 'product_id' and key not in fieldnames:
                      fieldnames.append(key)

          writer = csv.DictWriter(csvfile, fieldnames=fieldnames)
          writer.writeheader()

          for item in data:
              row = {'product_id': item['product_id']}
              for key, value in item.items():
                  if key != 'product_id':
                      if isinstance(value, dict):
                         # Преобразуем вложенный словарь в строку
                          row[key] = json.dumps(value, ensure_ascii=False)
                      elif isinstance(value, list):
                           # Преобразуем вложенные списки (из CashWithdrawal) в строку
                          row[key] = json.dumps(value, ensure_ascii=False)

                      else:
                          row[key] = value
              writer.writerow(row)
      print(f"Данные успешно записаны в {self.output_file}")

    def _write_to_json(self, data: List[Dict[str, Any]]):
      """Записывает данные в JSON."""
      if not data:
          print("Нет данных для записи.")
          return

      with open(self.output_file, 'w', encoding='utf-8') as jsonfile:
          json.dump(data, jsonfile, ensure_ascii=False, indent=4)
      print(f"Данные успешно записаны в {self.output_file}")

    async def run(self):
        """Запускает процесс извлечения и экспорта данных."""
        data = await self._extract_all_data()
        if self.output_format == 'csv':
            self._write_to_csv(data)
        elif self.output_format == 'json':
            self._write_to_json(data)

def load_product_ids(filename: str) -> List[str]:
    """Загружает ID продуктов из файла."""
    try:
        with open(filename, 'r') as f:
            return [line.strip() for line in f if line.strip() and not line.startswith('#')]
    except FileNotFoundError:
        print(f"Ошибка: Файл '{filename}' не найден.")
        exit(1)

def main():
    """Основная функция для обработки аргументов и запуска парсера."""
    parser = argparse.ArgumentParser(description="Извлекает данные о кредитных картах с vbr.ru.")
    parser.add_argument("-json", action="store_true", help="Сохранить в JSON")
    parser.add_argument("-csv", action="store_true", help="Сохранить в CSV (по умолчанию)")
    parser.add_argument("-o", "--output", default="output", help="Имя выходного файла (без .csv/.json)")
    parser.add_argument("-f", "--file", help="Файл с ID продуктов")
    parser.add_argument("product_ids", nargs="*", help="ID продуктов")
    args = parser.parse_args()

    if args.file:
        product_ids = load_product_ids(args.file)
    elif args.product_ids:
        product_ids = args.product_ids
    else:
        print("Ошибка: Укажите ID продуктов или файл с ними.")
        parser.print_help()
        exit(1)

    if args.json and args.csv:
        print("Ошибка: Нельзя использовать -json и -csv одновременно.")
        exit(1)
    output_format = "json" if args.json else "csv"
    output_file = args.output + "." + output_format

    extractor = AsyncCurlDataExtractor(product_ids, output_file, output_format)
    asyncio.run(extractor.run())

if __name__ == "__main__":
    main()