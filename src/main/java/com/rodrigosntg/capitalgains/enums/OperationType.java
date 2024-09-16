package com.rodrigosntg.capitalgains.enums;

public enum OperationType {
    BUY, SELL;

    public static OperationType fromString(String type) {
        try {
            return OperationType.valueOf(type.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid operation type: " + type);
        }
    }
}