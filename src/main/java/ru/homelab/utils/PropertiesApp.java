package ru.homelab.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesApp {
    private final String url;
    private final String username;
    private final String password;

    public PropertiesApp() {
        Properties prop = new Properties();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream stream = loader.getResourceAsStream("app.properties");
        try {
            prop.load(stream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.url = prop.getProperty("jdbc.url");
        this.username = prop.getProperty("jdbc.username");
        this.password = prop.getProperty("jdbc.password");
    }

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}