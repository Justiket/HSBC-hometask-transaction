package com.caoyinglong.exceptionUtils;

import com.caoyinglong.enums.ApiStatus;
import com.caoyinglong.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class BusinessExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(BusinessExceptionHandler.class);

    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public Result<String> dealBusinessException(BusinessException e) {
        Result<String> back;
        if (e.getMessage() == null) {
            back = Result.error(ApiStatus.ERROR);
        } else {
            back = Result.error(ApiStatus.INVALID_PARAM_ERROR);
        }
        return back;
    }
}
