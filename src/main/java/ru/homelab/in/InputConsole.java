package ru.homelab.in;

import java.util.Scanner;

public class InputConsole {
    public static int readingNumber() {
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

    public static String readingStr() {
        Scanner scanner = new Scanner(System.in);
        return scanner.next();
    }
}
