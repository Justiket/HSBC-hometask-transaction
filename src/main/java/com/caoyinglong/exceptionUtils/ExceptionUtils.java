package com.caoyinglong.exceptionUtils;


import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author caoyinglong
 * @description  异常处理工具类
 * @since 2025-01-22 14:17:19
 */
@NoArgsConstructor
public class ExceptionUtils {
    private static final Logger Log = LoggerFactory.getLogger(ExceptionUtils.class);


    public static void marsh(Exception ex) throws BusinessException {
        Throwable cause = unmarsh(ex);
        Log.error(cause.getMessage(), cause);
        throw new BusinessException(ex.getMessage());
    }

    public static Throwable unmarsh(Throwable ex) {
        Throwable cause = ex.getCause();
        if (cause != null) {
            cause = unmarsh(cause);
        } else {
            cause = ex;
        }
        return cause;
    }

    public static void wrappBusinessException(String message) {
        BusinessException ex = new BusinessException(message);
        Log.error(message, ex);
        throw new BusinessException(ex.getMessage());
    }

    public static void wrappBusinessException(String message, Exception e) {
        Log.error(message, e);
        throw new BusinessException(message);
    }

    public static void wrappException(Exception ex) {
        Log.error(ex.getMessage(), ex);
        throw new BusinessException(ex);
    }
}