package com.celi.cii.base.exception;

import lombok.Data;

/**
 * @Author jiangshengjun
 * @Date 2023/12/24
 * @Description 鉴权为通过
 */

@Data
public class PermissionException extends RuntimeException {

    private String message;

    public PermissionException() {
    }

    public PermissionException(String message) {
        this.message = message;
    }
}
