package ru.homelab.entity;

public class MeterType {
    private Long id;
    private MeterTypeName type;

    public MeterType(MeterTypeName type) {
        this.type = type;
    }

    public MeterType(Long id, MeterTypeName type) {
        this.id = id;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MeterTypeName getType() {
        return type;
    }

    public void setType(MeterTypeName type) {
        this.type = type;
    }
}
