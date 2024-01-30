package ru.homelab.out;

import ru.homelab.model.User;

/**
 * The type Menu.
 */
public class Menu {
    /**
     * Greeting menu.
     */
    public void greetingMenu() {
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
    public void mainMenuUser(User user) {
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
    public void mainMenuAdmin(User user) {
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
    public void addValueMenu(String login) {
        System.out.println("user: " + login);
        System.out.println("Add value menu:");
        System.out.println("Select an item and click enter:");
        System.out.println("1 - To add heat indicators");
        System.out.println("2 - To add cold water indicators");
        System.out.println("3 - To add hot water indicators");
        System.out.println("0 - Main menu");
    }
}
