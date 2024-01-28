package ru.homelab.utils.log;

import java.util.Date;

/**
 * The type Message.
 */
public record Message(Date date, Audit audit) {
}
