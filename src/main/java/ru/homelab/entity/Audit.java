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
    private Long userId;

    public Audit(String date, String messageAudit, Long userId) {
        this.date = date;
        this.messageAudit = messageAudit;
        this.userId = userId;
    }

    public Audit(Long id, String date, String messageAudit, Long userId) {
        this.id = id;
        this.date = date;
        this.messageAudit = messageAudit;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getMessageAudit() {
        return messageAudit;
    }

    public Long getUserId() {
        return userId;
    }
}
