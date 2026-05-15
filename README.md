### Hexlet tests and linter status:
[![Actions Status](https://github.com/AlexSorb/java-project-72/actions/workflows/hexlet-check.yml/badge.svg)](https://github.com/AlexSorb/java-project-72/actions)
[![Maintainability](https://api.codeclimate.com/v1/badges/fa77a7d8b611779df892/maintainability)](https://codeclimate.com/github/AlexSorb/java-project-72/maintainability)

# Анализатор страниц
Анализатор страниц — это веб-приложение, которое анализирует указанные URL-адреса на SEO-пригодность. Оно проверяет 
мета-теги, заголовки, описание и другие ключевые элементы страницы.

## Badges
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=AlexSorb_java-project-72&metric=coverage)](https://sonarcloud.io/summary/new_code?id=AlexSorb_java-project-72)


## Технологический стек
| Компонент   | Назначение                                           |
|-------------|------------------------------------------------------|
| Java 21     | Основной язык разработки (последняя LTS-версия)      |
| Gradle      | Сборщик проекта (> 8.5)                              |
| Javalin     | Лёгкий веб-фреймворк для Java                        |
| H2 Database | Встраиваемая реляционная БД (отлично для прототипов) |
| JUnit       | Модульное тестирование                               |
| Make        | Автоматизация команд                                 |

## Требования
- Java 21
- Gradle > 8.5

## Как запустить
### Установка
```bash
make install -C app
```
### Тестирование

```bash
  make test -C app
```

## Demo
Демонстрационную версию проекта можно увидеть по ссылке: [https://page-analyzer-011n.onrender.com](https://page-analyzer-011n.onrender.com) 
