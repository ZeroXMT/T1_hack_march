import requests
import argparse
import csv
import json
from bs4 import BeautifulSoup
from typing import List, Dict, Optional, Any
import time

class SyncCurlDataExtractor:
    BASE_URL = "https://www.vbr.ru/api/product/tab/"
    PRODUCT_TYPE = "CreditCard"
    IS_CALC_BTN_TARGET_BLANK = "False"
    TYPES = ["GeneralTerms", "RequirementsDocuments", "CashWithdrawal", "ProofOfIncome", "CardCashback"]
    
    def __init__(self, product_ids: List[str], output_file: str, output_format: str):
        self.product_ids = product_ids
        self.output_file = output_file
        self.output_format = output_format
        if output_format not in ['csv', 'json']:
            raise ValueError("output_format must be 'csv' or 'json'")

    def _make_request(self, product_id: str, type_: str) -> Optional[str]:
        params = {
            "productType": self.PRODUCT_TYPE,
            "productId": product_id,
            "type": type_,
            "isCalcBtnTargetBlank": self.IS_CALC_BTN_TARGET_BLANK,
        }
        print(f"Выполняется запрос: product_id={product_id}, type={type_}")
        try:
            response = requests.get(self.BASE_URL, params=params, timeout=10)
            response.raise_for_status()
            print(f"Запрос успешен: product_id={product_id}, type={type_}, статус={response.status_code}")
            return response.text
        except requests.exceptions.RequestException as e:
            print(f"Ошибка при запросе: product_id={product_id}, type={type_}: {e}")
            return None

    def _parse_general_terms(self, html_content: str) -> Dict[str, str]:
        """Улучшенный парсинг GeneralTerms с поддержкой множественных списков"""
        if not html_content:
            return {}
        soup = BeautifulSoup(html_content, 'html.parser')
        data = {}
        wrapper = soup.find('div', class_='product-card-conditions-wrapper')
        if wrapper:
            for ul in wrapper.find_all('ul', class_='product-card-conditions'):
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
        sections = soup.find_all('div', class_='m-bottom-small')
        for section in sections:
            header_div = section.find('b')
            if not header_div:
                continue
            header = header_div.get_text(strip=True)
            data[header] = []
            table = section.find_next_sibling('div', class_='product-card-table-overflow')
            if not table:
                continue
            table_body = table.find('table')
            if not table_body:
                continue
            headers = [th.get_text(strip=True) for th in table_body.find_all('th')]
            for row in table_body.find_all('tr'):
                row_data = {}
                cells = row.find_all('td')
                if not cells or len(cells) != len(headers):
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
        wrapper = soup.find('div', class_='product-card-conditions-wrapper')
        if wrapper:
            for ul in wrapper.find_all('ul', class_='product-card-conditions'):
                for li in ul.find_all('li'):
                    parts = list(li.stripped_strings)
                    if len(parts) >= 2:
                        key = parts[0].replace(":", "").strip()
                        value = " ".join(parts[1:]).strip()
                        data[key] = value
        return data

    def _parse_card_cashback(self, html_content: str) -> Dict[str, str]:
        """Парсинг CardCashback с правильным разделением ключ-значение."""
        if not html_content:
            return {}
        soup = BeautifulSoup(html_content, 'html.parser')
        data = {}
        wrapper = soup.find('div', class_='product-card-conditions-wrapper')
        if wrapper:
            for ul in wrapper.find_all('ul', class_='product-card-conditions'):
                for li in ul.find_all('li'):
                    # Получаем полный текст элемента
                    text = ' '.join(li.stripped_strings)
                    # Разделяем по первому двоеточию
                    if ':' in text:
                        key_part, value_part = text.split(':', 1)
                        key = key_part.strip()
                        value = value_part.strip()
                        data[key] = value
        return data

    def _parse_requirements_documents(self, html_content: str) -> Dict[str, str]:
        """Парсинг требований и документов с обработкой всех списков"""
        if not html_content:
            return {}
        soup = BeautifulSoup(html_content, 'html.parser')
        data = {}
        wrapper = soup.find('div', class_='product-card-conditions-wrapper')
        
        if wrapper:
            for ul in wrapper.find_all('ul', class_='product-card-conditions'):
                for li in ul.find_all('li'):
                    parts = list(li.stripped_strings)
                    if len(parts) >= 2:
                        key = parts[0].replace(":", "").strip()
                        value = " ".join(parts[1:]).strip()
                        data[key] = value
        return data

    def _parse_html(self, html_content: str, type_: str) -> Dict[str, Any]:
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

    def _extract_data_for_product(self, product_id: str) -> Dict[str, Any]:
        product_data = {"product_id": product_id}
        combined_data = {}
        for type_ in self.TYPES:
            html = self._make_request(product_id, type_)
            parsed_section = self._parse_html(html, type_)
            for key, value in parsed_section.items():
                combined_data[key] = value
        product_data.update(combined_data)
        return product_data

    def _extract_all_data(self) -> List[Dict[str, Any]]:
        all_data = []
        for product_id in self.product_ids:
            print(f"Начинаю обработку product_id: {product_id}")
            product_data = self._extract_data_for_product(product_id)
            all_data.append(product_data)
            print(f"Завершил обработку product_id: {product_id}. Задержка 6 секунд перед следующим продуктом...")
            time.sleep(6)
        return all_data

    def _write_to_csv(self, data: List[Dict[str, Any]]):
        if not data:
            print("Нет данных для записи.")
            return
            
        fieldnames = set()
        for item in data:
            fieldnames.update(item.keys())
        fieldnames = sorted(fieldnames)
        fieldnames.insert(0, fieldnames.pop(fieldnames.index('product_id')))

        with open(self.output_file, 'w', newline='', encoding='utf-8') as csvfile:
            writer = csv.DictWriter(csvfile, fieldnames=fieldnames)
            writer.writeheader()
            
            for item in data:
                row = {}
                for key in fieldnames:
                    value = item.get(key, '')
                    if isinstance(value, (dict, list)):
                        row[key] = json.dumps(value, ensure_ascii=False)
                    else:
                        row[key] = value
                writer.writerow(row)
        print(f"Данные успешно записаны в {self.output_file}")

    def _write_to_json(self, data: List[Dict[str, Any]]):
        if not data:
            print("Нет данных для записи.")
            return
        with open(self.output_file, 'w', encoding='utf-8') as jsonfile:
            json.dump(data, jsonfile, ensure_ascii=False, indent=4)
        print(f"Данные успешно записаны в {self.output_file}")

    def run(self):
        data = self._extract_all_data()
        if self.output_format == 'csv':
            self._write_to_csv(data)
        elif self.output_format == 'json':
            self._write_to_json(data)

def load_product_ids(filename: str) -> List[str]:
    try:
        with open(filename, 'r') as f:
            return [line.strip() for line in f if line.strip() and not line.startswith('#')]
    except FileNotFoundError:
        print(f"Ошибка: Файл '{filename}' не найден.")
        exit(1)

def main():
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
    extractor = SyncCurlDataExtractor(product_ids, output_file, output_format)
    extractor.run()

if __name__ == "__main__":
    main()