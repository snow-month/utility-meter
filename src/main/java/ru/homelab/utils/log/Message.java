package ru.homelab.utils.log;

import java.util.Date;

public record Message(Date date, Audit audit) {
}
