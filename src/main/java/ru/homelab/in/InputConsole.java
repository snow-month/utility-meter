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
        System.out.println("Введите номер месяца (1-12)");
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
            System.out.println("Для просмотра показаний пользователей введите имя пользоателя и нажмите энтер");
            Table.USERS.entrySet().stream()
                    .filter(stringUserEntry ->
                            !stringUserEntry.getValue().role().equals(Role.ADMIN))
                    .forEach(stringUserEntry ->
                            System.out.print(stringUserEntry.getKey() + " "));
            System.out.println();
            login = readingStr();
            if (!Table.USERS.containsKey(login)) {
                System.out.println("нет такого пользователя");
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
