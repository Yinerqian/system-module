package com.celi.cii.base.exception;

import lombok.Data;

/**
 * @Author jiangshengjun
 * @Description 服务异常
 */
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

    public ServiceException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public ServiceException(Exception e) {
        this.message = e.getMessage();
    }

    public ServiceException(String message) {
        this.message = message;
    }
}
