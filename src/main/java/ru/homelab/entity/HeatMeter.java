package ru.homelab.entity;

/**
 * The type Heat meter.
 */
public record HeatMeter(Long id, Integer value, Integer year, Integer month, Long userId) {
}
