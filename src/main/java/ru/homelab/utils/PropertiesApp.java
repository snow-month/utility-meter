package ru.homelab.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * The type Properties app.
 */
public class PropertiesApp {
    private final String url;
    private final String username;
    private String password;

    /**
     * Instantiates a new Properties app.
     */
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

    public PropertiesApp(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    /**
     * Gets url.
     *
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * Gets username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}