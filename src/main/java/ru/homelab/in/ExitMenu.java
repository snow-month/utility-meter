package ru.homelab.in;

public class ExitMenu {
    private final InputConsole inputConsole;

    public ExitMenu(InputConsole inputConsole) {
        this.inputConsole = inputConsole;
    }

    /**
     * Exit menu.
     */
    public void exitMenu() {
        System.out.println("0 - exit menu");
        inputConsole.readingNumber();
    }

    /**
     * Exit main menu.
     */
    public void exitMainMenu() {
        System.out.println("0 - Main menu");
        inputConsole.readingNumber();
    }

    /**
     * Exit add value menu.
     */
    public void exitAddValueMenu() {
        System.out.println("0 - AddValueMenu");
        inputConsole.readingNumber();
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
