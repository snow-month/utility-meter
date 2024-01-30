package ru.homelab.in;

import java.util.Scanner;

public class InputUtility {
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
}
