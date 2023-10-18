package com.celi.cii.base.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author jiangshengjun
 * @date 2023/10/10
 */
public interface IServiceExceptionHandler {

    @ExceptionHandler(value = {Exception.class})
    ResponseEntity handleException(Exception e);

}
