package ru.homelab.in;

import ru.homelab.model.Meter;
import ru.homelab.model.NameMeter;
import ru.homelab.model.Role;
import ru.homelab.model.Table;

import java.util.Map;
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

    public String readingStr(String message) {
        System.out.println(message);
        Scanner scanner = new Scanner(System.in);
        return scanner.next();
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

    public void addValue(String login, Map<String, Meter> map, NameMeter meter) {
        System.out.println("Enter the meter reading " + meter.name() + ":");
        boolean addValue = map.get(login).addValue(readingNumber());
        if (addValue) {
            System.out.println("Readings added");
        } else {
            System.out.println("Readings can be added once a month");
        }
    }
}
