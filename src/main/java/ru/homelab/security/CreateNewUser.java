package ru.homelab.security;

import ru.homelab.in.InputConsole;
import ru.homelab.model.MeterImpl;
import ru.homelab.model.Role;
import ru.homelab.model.Table;
import ru.homelab.model.User;

import java.util.ArrayList;

/**
 * The type Create new user.
 */
public class CreateNewUser {
    private final InputConsole inputConsole;

    /**
     * Instantiates a new Create new user.
     *
     * @param inputConsole the input console
     */
    public CreateNewUser(InputConsole inputConsole) {
        this.inputConsole = inputConsole;
    }

    /**
     * Create new user user.
     *
     * @return the user
     */
    public User createNewUser() {
        System.out.println("Creating a new user");
        System.out.println("Enter login");
        String login = inputConsole.readingStr();

        String password;
        String confirmation;
        do {
            System.out.println("Enter password");
            password = inputConsole.readingStr();
            System.out.println("Confirm the password");
            confirmation = inputConsole.readingStr();
            if (!password.equals(confirmation)) {
                System.out.println("Passwords don't match");
            }
        } while (!password.equals(confirmation));

        User user = new User(login, Role.USER, password);
        Table.USERS.put(login, user);
        Table.HEATING.put(login, new MeterImpl());
        Table.WATER_COLD.put(login, new MeterImpl());
        Table.WATER_HOT.put(login, new MeterImpl());
        Table.LOGGER.put(login, new ArrayList<>());
        return user;
    }
}
