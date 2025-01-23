package com.caoyinglong.statusenums;

import com.caoyinglong.utils.IStatus;

/**
 * @author caoyinglong
 * @description 接口返回状态
 * @since 2025-01-22 18:21:19
 */
public enum ApiStatus implements IStatus {

    SUCCESS(200, "操作成功"),
    TOO_MANY_REQUESTS(429, "请求过于频繁！"),
    INTERNAL_SERVER_ERROR(500, "服务器内部错误！"),
    REQ_PARAM_MISSING_ERROR(1001, "请求参数缺失！"),
    REQ_PARAM_INVALID_ERROR(1002, "请求参数无效！");

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
