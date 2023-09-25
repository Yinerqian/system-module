package com.celi.cii.common.exception;

import lombok.Data;

@Data
public class ServiceException extends RuntimeException {

    public ServiceException() {
    }

    public ServiceException(Exception e) {
        super(e.getMessage());
    }

    public ServiceException(String message) {
        super(message);
    }
}
