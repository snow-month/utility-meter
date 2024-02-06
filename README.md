# utility-meter
utility-meter - это программа для подачи и просмотра 
показаний счетчиков отопления, горячей и холодной воды в 
консольном режиме

## Сборка репозитория и локальный запуск
___
Выполните в консоли:

1. Клонируйте репозиторий с github

`git clone https://github.com/snow-month/utility-meter.git`

2. Запуск программы

2.1. Переходим в корневую папку проекта и в консоли запускаем команду

```
user@pc:~/IdeaProjects/utility-meter$ docker-compose up -d
```
2.2. Запускаем 

`Main.main()`

---

## Работа с программой
1. Стартовый экран

```
Greeting menu:
Default:
login: user, password: user, role: USER
login: admin, password: admin, role: ADMIN
Select an item and click enter:
1 - Sign in
2 - Sign up
0 - exit
```
В программе есть два пользователя:

Пользователь с login: user, password: user, role: USER с заполненными тестовыми данными.

И пользователь login: admin, password: admin, role: ADMIN.

2. Навигация

Навигация происходит путём ввода соответствующей цифры и нажатием клавиши enter.

3. Создание пользователя

Выбор цифры 2, и переходим в меню создания пользователя.
Вводим login и password.

```
Select an item and click enter:
1 - Sign in
2 - Sign up
0 - exit
2
Creating a new user. Enter login:
new_user
Enter password
123
Confirm the password
123
success
0 - exit menu
```
Вводим цифру 0 и выходим из меню создания пользователя.

4. Авторизация

```
Greeting menu:
Default:
login: user, password: user, role: USER
login: admin, password: admin, role: ADMIN
Выберите пункт и нажмите энтер:
1 - Sign in
2 - Sign up
0 - exit
1
Ваш логин
user
Введите пароль
user
```
5. Главное меню для просмотра и передачи показаний счётчиков.

```
user: user
Main menu:
Select an item and click enter:
1 - To get up-to-date meter readings
2 - To send meter readings
3 - To view readings for a specific month
4 - To view the history of the testimony
5 - View all users (ADMIN ONLY)
0 - logout
```
6. Получения актуальных показаний счетчиков.
```
1
Current meter readings:
HEATING: 122
WATER_COLD: There is no indication
WATER_HOT: There is no indication
0 - exit menu
```
7. Отправка показаний счётчиков.
```
2
user: user
Add value menu:
Select an item and click enter:
1 - To add heat indicators
2 - To add cold water indicators
3 - To add hot water indicators
0 - exit menu
1
Enter the meter reading HEATING:
2234
Readings added
0 - exit menu
```
8. Просмотр показаний за конкретный месяц.
```
3
Enter the year:
2023
Enter the month (1-12):
6
значение за месяц: 67
значение за месяц: нет значения
значение за месяц: нет значения
0 - exit menu
```
9. Просмотр истории подачи показаний.
```
4
Все показания HEATING:
year 2024, month 1, value: 2234
year 2023, month 11, value: 122
year 2023, month 6, value: 67
year 2023, month 4, value: 42
year 2023, month 2, value: 22
Нет показаний WATER_COLD.
Нет показаний WATER_HOT.
0 - exit menu
```
10. Меню для admin
```
5
user: admin, role: ADMIN
Main menu admin:
Select an item and click enter:
1 - To view user readings
0 - exit menu
```
11. Просмотр показаний пользователей.
```
5
user: admin, role: ADMIN
Main menu admin:
Select an item and click enter:
1 - To view user readings
0 - exit menu
1
Все показания HEATING:
year 2023, month 2, value: 22
year 2023, month 4, value: 42
year 2023, month 6, value: 67
year 2023, month 11, value: 122
year 2024, month 1, value: 2234
Нет показаний WATER_COLD.
Нет показаний WATER_HOT.
0 - exit menu
```
