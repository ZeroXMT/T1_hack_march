import json
import csv
import os
import argparse

def json_to_csv(json_file_path, csv_file_path):
    """
    Конвертирует JSON файл в CSV файл.

    Предполагает, что JSON файл содержит массив объектов, где ключи объектов
    становятся заголовками CSV, а значения - строками.

    Args:
        json_file_path (str): Путь к входному JSON файлу.
        csv_file_path (str): Путь для сохранения выходного CSV файла.
    """

    if not os.path.exists(json_file_path):
        raise FileNotFoundError(f"JSON файл не найден по пути: {json_file_path}")

    try:
        with open(json_file_path, 'r', encoding='utf-8') as json_file:
            data = json.load(json_file)
    except json.JSONDecodeError:
        raise ValueError(f"Ошибка декодирования JSON в файле: {json_file_path}. Проверьте формат JSON.")

    if not isinstance(data, list):
        raise ValueError("Ожидается, что JSON файл содержит массив объектов.")

    if not data:
        print("JSON файл содержит пустой массив. CSV файл будет создан только с заголовками, если они могут быть определены.")
        headers = set() # На случай, если в пустом массиве все же есть ключи в структуре (маловероятно, но для надежности)
    else:
        # Получаем заголовки из ключей первого объекта в массиве.
        # Предполагаем, что все объекты в массиве имеют одинаковые ключи (или подмножество).
        headers = data[0].keys()

    with open(csv_file_path, 'w', newline='', encoding='utf-8') as csv_file:
        csv_writer = csv.writer(csv_file)

        if headers:
            csv_writer.writerow(headers)  # Записываем заголовки в CSV

        for item in data:
            row = [item.get(header, '') for header in headers] # Используем .get() для обработки отсутствующих ключей
            csv_writer.writerow(row)

    print(f"JSON файл '{json_file_path}' успешно конвертирован в CSV файл '{csv_file_path}'")


if __name__ == "__main__":
    parser = argparse.ArgumentParser(description='Конвертация JSON файла в CSV.')
    parser.add_argument('-j', '--json_file', type=str, help='Путь к входному JSON файлу', required=True)
    parser.add_argument('-c', '--csv_file', type=str, help='Путь к выходному CSV файлу', required=True)

    args = parser.parse_args()
    json_input_file = args.json_file
    csv_output_file = args.csv_file

    try:
        json_to_csv(json_input_file, csv_output_file)
    except FileNotFoundError as e:
        print(f"Ошибка: {e}")
    except ValueError as e:
        print(f"Ошибка: {e}")
    except Exception as e:
        print(f"Непредвиденная ошибка: {e}")