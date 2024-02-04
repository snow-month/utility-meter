package ru.homelab.in;

import ru.homelab.entity.NameMeter;


/**
 * Класс для считывания данных из консоли.
 *
 * @author Petr "mypost@home.ru"
 * @version 1.0
 */
public class InputConsole extends InputUtility {
    /**
     * Метод для ввода нового пароля.
     *
     * @return the string
     */
    public String newPasswordEntry() {
        String password;
        String confirmation;
        do {
            password = stringEntry("Enter password");
            confirmation = stringEntry("Confirm the password");
            if (!password.equals(confirmation)) {
                System.out.println("Passwords don't match");
            }
        } while (!password.equals(confirmation));

        return password;
    }

    public Integer valueInput(NameMeter meter) {
        System.out.println("Enter the meter reading " + meter.name() + ":");
        return inputNumberNotString();
    }
}
