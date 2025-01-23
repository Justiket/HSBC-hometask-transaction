package com.caoyinglong.enums;

import com.caoyinglong.utils.IStatus;

/**
 * @author caoyinglong
 * @description 微服务间通讯状态枚举
 * @since 2025-01-22 18:21:19
 */
public enum Status implements IStatus {
    SUCCESS(200, "success"),
    ERROR(500, "error");

    private final int status;
    private final String message;

    Status(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
