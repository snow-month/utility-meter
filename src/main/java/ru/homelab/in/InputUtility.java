package ru.homelab.in;

import java.util.Scanner;

/**
 * Класс для утилит чтения с консоли.
 *
 * @author Petr "mypost@home.ru"
 * @version 1.0
 */
public class InputUtility {
    /**
     * Метод для считывания числа,
     * при некорректных данных - данные будут повторно считаны с консоли.
     *
     * @return the int
     */
    public int inputNumberNotString() {
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
     * Метод для считывания строки.
     *
     * @param message сообщение которое, отобразиться перед вводом
     * @return the string
     */
    public String stringEntry(String message) {
        System.out.println(message);
        Scanner scanner = new Scanner(System.in);
        return scanner.next();
    }

    /**
     * Number entry integer.
     *
     * @param message the message
     * @return the integer
     */
    public Integer numberEntry(String message) {
        System.out.println(message);
        return inputNumberNotString();
    }
}
