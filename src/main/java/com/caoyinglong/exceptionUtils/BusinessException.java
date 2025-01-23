package com.caoyinglong.exceptionUtils;


/**
 * @author caoyinglong
 * @description 自定义业务异常
 * @since 2025-01-22 14:17:19
 */
public class BusinessException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public BusinessException(String msg) {
        super(msg);
    }

    public BusinessException(Throwable e) {
        super(e);
    }

    public BusinessException(String msg, Throwable e) {
        super(msg, e);
    }
}