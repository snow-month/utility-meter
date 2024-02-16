# utility-meter

utility-meter - это программа для подачи и просмотра
показаний счетчиков отопления, горячей и холодной воды

---
## Сборка репозитория и локальный запуск

___
Выполните в консоли:

1. Клонируйте репозиторий с github

`git clone https://github.com/snow-month/utility-meter.git`

2. Собираем war

![img.png](img.png)

```
BUILD SUCCESSFUL in 221ms
3 actionable tasks: 3 up-to-date
23:16:35: Execution finished 'war'.

```

3.Запуск программы

Переходим в корневую папку проекта и в консоли запускаем команду

```
user@pc:~/IdeaProjects/utility-meter$ docker compose up -d
```

## Работа с программой

---

1. Стартовый экран
```
http://localhost:8080/
```

![img_1.png](img_1.png)

2. Получение всех показаний

```
http://localhost:8080/heating
```

![img_2.png](img_2.png)

2. Получение текущего значения

```
http://localhost:8080/heating/current
```

![img_3.png](img_3.png)

3. Получение значения за месяц

```
http://localhost:8080/heating/month
```

![img_4.png](img_4.png)

![img_5.png](img_5.png)

