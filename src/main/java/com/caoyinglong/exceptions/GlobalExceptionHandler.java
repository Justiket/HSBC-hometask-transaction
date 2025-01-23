package com.caoyinglong.exceptions;

import com.caoyinglong.statusenums.ApiStatus;
import com.caoyinglong.utils.IStatus;
import com.caoyinglong.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public Result<String> dealBusinessException(BusinessException e) {
        Result<String> back;
        if (e.getMessage() == null) {
            back = Result.error(ApiStatus.INTERNAL_SERVER_ERROR);
        } else {
            IStatus status = e.getStatus();
            back = Result.error(status, e.getMessage());
        }
        log.error(e.getMessage(), e);
        return back;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public Result<Map<String,String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        log.error(ApiStatus.REQ_PARAM_MISSING_ERROR.getMessage(),ex);
        return Result.error(ApiStatus.REQ_PARAM_MISSING_ERROR,errors);
    }
}
