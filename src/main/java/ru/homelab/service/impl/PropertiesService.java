package ru.homelab.service.impl;

import java.io.IOException;
import java.util.Properties;

public final class PropertiesService {
    private PropertiesService() {
    }

    private static final Properties PROPERTIES = new Properties();

    static {
        loadProperties();
    }

    public static String get(String key) {
        return PROPERTIES.getProperty(key);
    }

    private static void loadProperties() {
        try (var stream = PropertiesService.class.getClassLoader()
                .getResourceAsStream("app.properties")) {
            PROPERTIES.load(stream);
        } catch (IOException e) {
            System.out.println("sql exception load Properties: " + e.getMessage());
        }
    }


}
