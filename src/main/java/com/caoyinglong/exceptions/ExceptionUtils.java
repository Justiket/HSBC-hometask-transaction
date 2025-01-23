package com.caoyinglong.exceptions;


import com.caoyinglong.utils.IStatus;
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

    public static void wrappBusinessException(String message) {
        BusinessException ex = new BusinessException(message);
        Log.error(message, ex);
        throw new BusinessException(ex.getMessage());
    }

    public static void wrappBusinessException(String message, IStatus status) {
        BusinessException ex = new BusinessException(message,status);
        Log.error(message, ex);
        throw new BusinessException(ex.getMessage(),status);
    }
}