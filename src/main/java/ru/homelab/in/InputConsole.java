package ru.homelab.in;

import ru.homelab.model.Role;
import ru.homelab.model.Table;

import java.util.Scanner;


/**
 * The type Input console.
 */
public class InputConsole {
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

    /**
     * Reading number int.
     *
     * @return the int
     */
    public int readingNumber() {
        Scanner scanner = new Scanner(System.in);
        int point;

        while (true) {
            if (scanner.hasNextInt()) {
                point = scanner.nextInt();
                break;
            } else {
                System.out.println("enter a number");
                scanner.next();
            }
        }
        return point;
    }

    /**
     * Reading str string.
     *
     * @return the string
     */
    public String readingStr() {
        Scanner scanner = new Scanner(System.in);
        return scanner.next();
    }
}
