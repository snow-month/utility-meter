package ru.homelab.in;

public class ExitMenu extends InputUtility {
    /**
     * Exit menu.
     */
    public void exitMenu() {
        System.out.println("0 - exit menu");
        readingNumber();
    }

    /**
     * Exit main menu.
     */
    public void exitMainMenu() {
        System.out.println("0 - Main menu");
        readingNumber();
    }

    /**
     * Exit add value menu.
     */
    public void exitAddValueMenu() {
        System.out.println("0 - AddValueMenu");
        readingNumber();
    }

    /**
     * No such point.
     */
    public void noSuchPoint() {
        System.out.println("No such point");
        exitMenu();
    }

    /**
     * Incorrect username or password.
     */
    public void incorrectUsernameOrPassword() {
        System.out.println("Incorrect username or password");
        exitMenu();
    }
}
