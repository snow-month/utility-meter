package ru.homelab.in;

/**
 * Класс для отображения меню для выхода.
 *
 * @author Petr "mypost@home.ru"
 * @version 1.0
 */
public class ExitMenu extends InputUtility {
    /**
     * Выход из меню.
     */
    public void exitMenu() {
        System.out.println("0 - exit menu");
        readingNumber();
    }

    /**
     * Выход в главное меню.
     */
    public void exitMainMenu() {
        System.out.println("0 - Main menu");
        readingNumber();
    }

    /**
     * Выход в меню добавления показаний.
     */
    public void exitAddValueMenu() {
        System.out.println("0 - AddValueMenu");
        readingNumber();
    }

    /**
     * Меню если была выбрана некорректная цифра.
     */
    public void noSuchPoint() {
        System.out.println("No such point");
        exitMenu();
    }

    /**
     * Меню если был введён неправильный login и password.
     */
    public void incorrectUsernameOrPassword() {
        System.out.println("Incorrect username or password");
        exitMenu();
    }
}
