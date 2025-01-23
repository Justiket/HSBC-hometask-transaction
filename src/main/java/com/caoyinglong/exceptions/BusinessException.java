package com.caoyinglong.exceptions;


import com.caoyinglong.utils.IStatus;
import lombok.Getter;

import java.io.Serial;

/**
 * @author caoyinglong
 * @description 自定义业务异常
 * @since 2025-01-22 14:17:19
 */
@Getter
public class BusinessException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    private IStatus status;

    public BusinessException(String msg) {
        super(msg);
    }

    public BusinessException(Throwable e) {
        super(e);
    }

    public BusinessException(String msg, IStatus status) {
        super(msg);
        this.status = status;
    }

}