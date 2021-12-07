package com.celi.system.exception;

import com.celi.system.enums.ServiceCode;
import lombok.Data;

@Data
public class ServiceException extends RuntimeException {

    private Integer code;

    private String message;

    public ServiceException() {

    }

    public ServiceException(ServiceException e) {
        this.code = e.getCode();
        this.message = e.getMessage();
    }

    public ServiceException(ServiceCode serviceCode) {
        this.code = serviceCode.getCode();
        this.message = serviceCode.getMessage();
    }

    public ServiceException(Exception e) {
        this.message = e.getMessage();
    }

    public ServiceException(String message) {
        this.message = message;
    }
}
