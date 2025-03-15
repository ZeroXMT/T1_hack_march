import argparse

def parse_codes_from_file(file_path):
    """
    Парсит коды из файла, удаляя номера строк и префикс '29_'.

    Args:
        file_path (str): Путь к входному файлу.

    Returns:
        list: Список запарсенных кодов.
    """
    parsed_codes = []
    try:
        with open(file_path, 'r') as file:
            for line_number, line in enumerate(file):
                stripped_line = line.strip()
                if not stripped_line:  # Пропускаем пустые строки
                    continue

                if stripped_line.isdigit():  # Пропускаем строки, содержащие только цифры
                    continue

                if stripped_line.startswith('29_'):
                    code = stripped_line[3:]  # Удаляем префикс '29_'
                    parsed_codes.append(code)
                else:
                    print(f"Строка '{stripped_line}' (строка {line_number + 1}) не соответствует ожидаемому формату и будет пропущена.")

    except FileNotFoundError:
        print(f"Ошибка: Файл '{file_path}' не найден.")
        return None
    except Exception as e:
        print(f"Произошла ошибка при чтении файла: {e}")
        return None

    return parsed_codes

def save_codes_to_file(codes, output_file_path):
    """
    Сохраняет список кодов в файл.

    Args:
        codes (list): Список кодов для сохранения.
        output_file_path (str): Путь к выходному файлу.
    """
    try:
        with open(output_file_path, 'w') as output_file:
            for code in codes:
                output_file.write(code + '\n')
        print(f"Запарсeнные коды сохранены в файл: '{output_file_path}'")
    except Exception as e:
        print(f"Произошла ошибка при записи в файл '{output_file_path}': {e}")


if __name__ == '__main__':
    parser = argparse.ArgumentParser(description='Парсинг кодов из файла с удалением номеров строк и префикса "29_", и сохранение в другой файл.')
    parser.add_argument('-f', '--file', type=str, help='Путь к входному файлу с кодами', required=True)
    parser.add_argument('-o', '--output', type=str, help='Путь к выходному файлу для сохранения запарсенных кодов', required=True)

    args = parser.parse_args()
    input_file_path = args.file
    output_file_path = args.output

    parsed_codes = parse_codes_from_file(input_file_path)

    if parsed_codes:
        save_codes_to_file(parsed_codes, output_file_path)