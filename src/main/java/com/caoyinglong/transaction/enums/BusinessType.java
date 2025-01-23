package com.caoyinglong.transaction.enums;

public enum BusinessType {
    DEPOSIT,
    WITHDRAW,
    LOAN;
    public static BusinessType getType(String type) {
        for (BusinessType value : values()) {
            if (value.name().equals(type)) {
                return value;
            }
        }
        return null;
    }
}
