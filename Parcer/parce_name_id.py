import requests
import json
import time
from bs4 import BeautifulSoup

BASE_URL = "{city}vbr.ru/banki/kreditnyekarty/{page}/"
HEADERS = {
    "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/119.0.0.0 Safari/537.36"
}

def safe_text(element, selector, default="N/A"):
    el = element.select_one(selector)
    return el.get_text(strip=True) if el else default

def safe_attr(element, selector, attr, default="N/A"):
    el = element.select_one(selector)
    return el[attr] if el and attr in el.attrs else default

def build_url(city="", page=1):
    city_part = f"{city}." if city else ""
    page_part = f"{page}/" if page > 1 else ""
    return f"https://{city_part}{BASE_URL.format(city=city_part, page=page_part)}"

def parse_card(card):
    return {
        "product_id": safe_attr(card, ".product-card-compare", "data-product-id"),
        "product_name": safe_text(card, ".product-card-title-link"),
        "bank": safe_text(card, ".product-card-data-org"),
        "reviews_count": safe_text(card, ".product-card-reviews-number").split()[0],
        "rating": safe_attr(card, ".rating-diagram", "data-rating-val"),
        "credit_limit": safe_text(card, ".product-card-col:has(.product-card-col-title:-soup-contains('Кредитный лимит')) .product-card-col-text"),
        "grace_period": safe_text(card, ".product-card-col:has(.product-card-col-title:-soup-contains('Льготный период')) .product-card-col-text"),
        "service_fee": safe_text(card, ".product-card-col:has(.product-card-col-title:-soup-contains('Обслуживание')) .product-card-col-text")
    }

def parse_page(url):
    response = requests.get(url, headers=HEADERS)
    soup = BeautifulSoup(response.text, 'lxml')
    return [parse_card(card) for card in soup.select(".product-card-part-wrapper")]

def main():
    city = input("Введите город (например 'sankt-peterburg' или оставьте пустым): ").strip()
    max_pages = int(input("Введите количество страниц для парсинга: "))
    
    all_data = []
    for page in range(1, max_pages + 1):
        print(f"Парсинг страницы {page}...")
        url = build_url(city, page)
        all_data.extend(parse_page(url))
        if page < max_pages:
            time.sleep(3)  # Задержка между страницами
    
    filename = f"{city}_credit_cards_{max_pages}_pages.json" if city else f"all_credit_cards_{max_pages}_pages.json"
    with open(filename, 'w', encoding='utf-8') as f:
        json.dump(all_data, f, ensure_ascii=False, indent=2)
    print(f"Сохранено {len(all_data)} записей в {filename}")

if __name__ == "__main__":
    main()