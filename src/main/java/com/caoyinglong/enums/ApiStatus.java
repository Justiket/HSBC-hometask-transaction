package com.caoyinglong.enums;

import com.caoyinglong.utils.IStatus;

/**
 * @author caoyinglong
 * @description 接口返回状态
 * @since 2025-01-22 18:21:19
 */
public enum ApiStatus implements IStatus {

    SUCCESS(200, "操作成功"),
    ERROR(500, "系统异常！"),
    INVALID_PARAM_ERROR(400, "参数错误！");

    ApiStatus(int status, String message) {
        this.status = status;
        this.message = message;
    }

    private final int status;
    private final String message;

    @Override
    public int getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
