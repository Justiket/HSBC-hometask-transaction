package com.caoyinglong.statusenums;

import com.caoyinglong.utils.IStatus;
import lombok.Getter;

/**
 * @author caoyinglong
 * @description 微服务间通讯状态枚举
 * @since 2025-01-22 18:21:19
 */
@Getter
public enum Status implements IStatus {
    SUCCESS(200, "success"),
    ERROR(500, "error");

    private final int status;
    private final String message;

    Status(int status, String message) {
        this.status = status;
        this.message = message;
    }

}
