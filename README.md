# Website Change Monitor

Приложение для сравнения состояния веб-страниц между двумя моментами времени и генерации отчета об изменениях.

## Requirments
- Java 21+
- Gradle 8+

## Quick start

1. Клонировать репозиторий:
```bash
git clone [repo-url]
cd website-monitor
```

2. Собрать и запустить:
```bash
./gradlew clean bootRun
```

## Пример использования

```java
public static void main(String[] args) {
    Map<URI, String> yesterday = Map.of(
        URI.create("https://site.com/old"), "<html>Old</html>"
    );
    
    Map<URI, String> today = Map.of(
        URI.create("https://site.com/new"), "<html>New</html>"
    );

    ApplicationContext ctx = SpringApplication.run(Application.class, args);
    WebsiteMonitoringService service = ctx.getBean(WebsiteMonitoringService.class);
    
    service.buildDailyReport(yesterday, today)
          .ifPresent(System.out::println);
}
```

Вывод:
```
Здравствуйте, дорогая и.о. секретаря

За последние сутки произошли изменения:
Исчезли: https://site.com/old
Появились: https://site.com/new
Изменились: нет изменений
```
