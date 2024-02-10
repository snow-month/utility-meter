package ru.homelab.entity;

// todo вторичный ключ
public class MeterValue {
    private Long id;
    private Integer value;
    private Integer year;
    private Integer month;
    private Long meterTypeId;
    private Long userId;

    public MeterValue(Long id, Integer value, Integer year, Integer month, Long meterTypeId, Long userId) {
        this.id = id;
        this.value = value;
        this.year = year;
        this.month = month;
        this.meterTypeId = meterTypeId;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Long getMeterTypeId() {
        return meterTypeId;
    }

    public void setMeterTypeId(Long meterTypeId) {
        this.meterTypeId = meterTypeId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
