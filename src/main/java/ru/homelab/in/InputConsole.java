package ru.homelab.in;

import ru.homelab.model.Role;
import ru.homelab.model.Table;

import java.util.List;
import java.util.Map;


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
        List<String> logins;
        String login;
        do {
            System.out.println("To view user readings, enter the user name and click enter");
            logins = Table.USERS.entrySet().stream()
                    .filter(stringUserEntry ->
                            !stringUserEntry.getValue().role().equals(Role.ADMIN))
                    .map(Map.Entry::getKey)
                    .toList();

            logins.forEach(s -> System.out.print(s + " "));
            System.out.println();

            login = readingStr();
            if (!logins.contains(login)) {
                System.out.println("there is no such user");
            }
        } while (!logins.contains(login));
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
