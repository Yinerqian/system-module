package com.celi.system.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class AuthenticationException extends RuntimeException {

    private Integer code = HttpStatus.UNAUTHORIZED.value();

    private String message;

    public AuthenticationException(String message) {
        this.message = message;
    }

}
