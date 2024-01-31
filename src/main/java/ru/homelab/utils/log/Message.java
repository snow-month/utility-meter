package ru.homelab.utils.log;

import java.util.Date;

/**
 * Класс для сообщения для логирования.
 *
 * @author Petr "mypost@home.ru"
 * @version 1.0
 */
public record Message(Date date, Audit audit) {
}
