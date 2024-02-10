package ru.homelab;

import ru.homelab.entity.User;
import ru.homelab.repository.impl.UserRepositoryImpl;
import ru.homelab.service.impl.DBConnectionProviderImpl;
import ru.homelab.service.impl.MigrationService;
import ru.homelab.service.impl.PropertiesService;
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
        new MigrationService().init(
                PropertiesService.get("db.url"),
                PropertiesService.get("db.username"),
                PropertiesService.get("db.password"),
                "db/changelog/changelog.xml",
                PropertiesService.get("liquibase.defaultSchemaName")
        );

        // задержка для создания таблиц
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
        }

        User user = new User("www", "223322", "TT");
        new UserRepositoryImpl(new DBConnectionProviderImpl()).save(user);
    }
}