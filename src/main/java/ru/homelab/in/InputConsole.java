package ru.homelab.in;

import ru.homelab.model.Role;
import ru.homelab.model.Table;


/**
 * The type Input console.
 */
public class InputConsole extends InputUtility {
    /**
     * Enter the month number int.
     *
     * @return the int
     */
    public int enterTheMonthNumber() {
        System.out.println("Enter the month number (1-12)");
        return readingNumber();
    }

    /**
     * Enter user string.
     *
     * @return the string
     */
    public String enterUser() {
        String login;
        do {
            System.out.println("To view user readings, enter the user name and click enter");
            Table.USERS.entrySet().stream()
                    .filter(stringUserEntry ->
                            !stringUserEntry.getValue().role().equals(Role.ADMIN))
                    .forEach(stringUserEntry ->
                            System.out.print(stringUserEntry.getKey() + " "));
            System.out.println();
            login = readingStr();
            if (!Table.USERS.containsKey(login)) {
                System.out.println("there is no such user");
            }
        } while (!Table.USERS.containsKey(login));
        return login;
    }

    public String readingNewPassword() {
        String password;
        String confirmation;
        do {
            password = readingStr("Enter password");
            confirmation = readingStr("Confirm the password");
            if (!password.equals(confirmation)) {
                System.out.println("Passwords don't match");
            }
        } while (!password.equals(confirmation));

        return password;
    }
}
