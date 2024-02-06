package ru.homelab.entity;

/**
 * Класс для сообщения для логирования.
 *
 * @author Petr "mypost@home.ru"
 * @version 1.0
 */
public final class Audit {
    private Long id;
    private final String date;
    private final String messageAudit;
    private final Long userId;

    /**
     * Instantiates a new Audit.
     *
     * @param date         the date
     * @param messageAudit the message audit
     * @param userId       the user id
     */
    public Audit(String date, String messageAudit, Long userId) {
        this.date = date;
        this.messageAudit = messageAudit;
        this.userId = userId;
    }

    /**
     * Instantiates a new Audit.
     *
     * @param id           the id
     * @param date         the date
     * @param messageAudit the message audit
     * @param userId       the user id
     */
    public Audit(Long id, String date, String messageAudit, Long userId) {
        this.id = id;
        this.date = date;
        this.messageAudit = messageAudit;
        this.userId = userId;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Gets date.
     *
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * Gets message audit.
     *
     * @return the message audit
     */
    public String getMessageAudit() {
        return messageAudit;
    }

    /**
     * Gets user id.
     *
     * @return the user id
     */
    public Long getUserId() {
        return userId;
    }
}
