package ru.homelab.utils.log;

import ru.homelab.model.Table;

import java.util.Date;
import java.util.List;

/**
 * The type Logger.
 */
public class Logger {
    /**
     * Log.
     *
     * @param login the login
     * @param audit the audit
     */
    public static void log(String login, Audit audit) {
        Message message = new Message(new Date(), audit);
        List<Message> messages = Table.LOGGER.get(login);
        messages.add(message);
        Table.LOGGER.put(login, messages);
    }
}
