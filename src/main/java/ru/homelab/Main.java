package ru.homelab;

import ru.homelab.service.impl.MigrationService;
import ru.homelab.utils.PropertiesApp;
// todo многопоточность, чтобы много пользователей одновременно
// todo commit transactional

/**
 * Класс запуска программы.
 *
 * @author Petr "mypost@home.ru"
 * @version 1.0
 */
public class Main {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        PropertiesApp propApp = new PropertiesApp();
        MigrationService migrationService = new MigrationService();
        migrationService.init(propApp.getUrl(), propApp.getUsername(), propApp.getPassword(),
                "db/changelog/changelog.xml", propApp.getDefaultSchemaName());

        // задержка для создания таблиц
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
        }
    }
}