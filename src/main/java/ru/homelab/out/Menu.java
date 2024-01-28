package ru.homelab.out;

import ru.homelab.in.InputConsole;
import ru.homelab.model.User;

/**
 * The type Menu.
 */
public class Menu {
    private final InputConsole inputConsole;

    /**
     * Instantiates a new Menu.
     *
     * @param inputConsole the input console
     */
    public Menu(InputConsole inputConsole) {
        this.inputConsole = inputConsole;
    }

    /**
     * Greeting menu.
     */
    public static void greetingMenu() {
        System.out.println("Greeting menu:");
        System.out.println("Default:");
        System.out.println("login: user, password: user, role: USER");
        System.out.println("login: admin, password: admin, role: ADMIN");
        System.out.println("Select an item and click enter:");
        System.out.println("1 - Sign in");
        System.out.println("2 - Sign up");
        System.out.println("0 - exit");
    }

    /**
     * Main menu user.
     *
     * @param user the user
     */
    public static void mainMenuUser(User user) {
        System.out.println("user: " + user.login());
        System.out.println("Main menu:");
        System.out.println("Select an item and click enter:");
        System.out.println("1 - To get up-to-date meter readings");
        System.out.println("2 - To send meter readings");
        System.out.println("3 - To view readings for a specific month");
        System.out.println("4 - To view the history of the testimony");
        System.out.println("0 - logout");
    }

    /**
     * Main menu admin.
     *
     * @param user the user
     */
    public static void mainMenuAdmin(User user) {
        System.out.println("user: " + user.login() + ", role: ADMIN");
        System.out.println("Main menu:");
        System.out.println("Select an item and click enter:");
        System.out.println("1 - To view user readings");
        System.out.println("0 - logout");
    }

    /**
     * Add value menu.
     *
     * @param login the login
     */
    public static void addValueMenu(String login) {
        System.out.println("user: " + login);
        System.out.println("Add value menu:");
        System.out.println("Select an item and click enter:");
        System.out.println("1 - To add heat indicators");
        System.out.println("2 - To add cold water indicators");
        System.out.println("3 - To add hot water indicators");
        System.out.println("0 - Main menu");
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
        System.out.println("0 - Add value menu");
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
