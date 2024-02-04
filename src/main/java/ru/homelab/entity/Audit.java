package ru.homelab.entity;

/**
 * Класс для сообщения для логирования.
 *
 * @author Petr "mypost@home.ru"
 * @version 1.0
 */
public final class Audit {
    private Long id;
    private String date;
    private String messageAudit;
    private Long user_id;

    public Audit(String date, String messageAudit, Long user_id) {
        this.date = date;
        this.messageAudit = messageAudit;
        this.user_id = user_id;
    }

    public Audit(Long id, String date, String messageAudit, Long user_id) {
        this.id = id;
        this.date = date;
        this.messageAudit = messageAudit;
        this.user_id = user_id;
    }
}
