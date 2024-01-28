package ru.homelab.out;

import ru.homelab.in.InputConsole;
import ru.homelab.model.User;

public class Menu {
    private final InputConsole inputConsole;

    public Menu(InputConsole inputConsole) {
        this.inputConsole = inputConsole;
    }

    public static void greetingMenu() {
        System.out.println("Greeting menu:");
        System.out.println("Default:");
        System.out.println("login: user, password: user, role: USER");
        System.out.println("login: admin, password: admin, role: ADMIN");
        System.out.println("Выберите пункт и нажмите энтер:");
        System.out.println("1 - Sign in");
        System.out.println("2 - Sign up");
        System.out.println("0 - exit");
    }

    public static void mainMenuUser(User user) {
        System.out.println("user: " + user.login());
        System.out.println("Main menu:");
        System.out.println("Выберите пункт и нажмите энтер:");
        System.out.println("1 - для получения актуальных показаний счетчиков");
        System.out.println("2 - для отправки показаний счётчиков");
        System.out.println("3 - для просмотра показаний за конкретный месяц");
        System.out.println("4 - для просмотра истории подачи показаний");
        System.out.println("0 - logout");
    }

    public static void mainMenuAdmin(User user) {
        System.out.println("user: " + user.login() + ", role: ADMIN");
        System.out.println("Main menu:");
        System.out.println("Выберите пункт и нажмите энтер:");
        System.out.println("1 - Для просмотра показаний пользователей");
        System.out.println("0 - logout");
    }

    public static void addValueMenu(String login) {
        System.out.println("user: " + login);
        System.out.println("Add value menu:");
        System.out.println("Выберите пункт и нажмите энтер:");
        System.out.println("1 - для добавление показателей тепла");
        System.out.println("2 - для добавление показателей холодной воды");
        System.out.println("3 - для добавление показателей горячей воды");
        System.out.println("0 - Main menu");
    }

    public void exitMenu() {
        System.out.println("0 - exit menu");
        inputConsole.readingNumber();
    }

    public void exitMainMenu() {
        System.out.println("0 - Main menu");
        inputConsole.readingNumber();
    }

    public void exitAddValueMenu() {
        System.out.println("0 - Add value menu");
        inputConsole.readingNumber();
    }

    public void noSuchPoint() {
        System.out.println("No such point");
        exitMenu();
    }

    public void incorrectUsernameOrPassword() {
        System.out.println("Incorrect username or password");
        exitMenu();
    }
}
