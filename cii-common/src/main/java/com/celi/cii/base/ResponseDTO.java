package com.celi.cii.base;

import com.celi.cii.base.exception.ServiceCode;
import lombok.Data;

@Data
public class ResponseDTO<T> {

    private Boolean successFlag;

    private T content;

    private String message;

    private Integer code;

    private ResponseDTO() {
        //必须使用下面几个方法创建对象，不可以直接new
    }

    public static <T> ResponseDTO<T> ok(T content) {
        ResponseDTO dto = new ResponseDTO();
        dto.setContent(content);
        dto.successFlag = true;
        dto.message = ServiceCode.SUCCESS.getMessage();

        return dto;
    }

    public static <T> ResponseDTO ok() {
        ResponseDTO dto = new ResponseDTO();
        dto.successFlag = true;
        dto.message = ServiceCode.SUCCESS.getMessage();

        return dto;
    }

    public static <T> ResponseDTO<T> errorCode(ServiceCode errorCode) {
        ResponseDTO dto = new ResponseDTO();
        dto.successFlag = false;
        dto.message = errorCode.getMessage();
        dto.code = errorCode.getCode();

        return dto;
    }

    public static <T> ResponseDTO<T> errorMessage(String message) {
        ResponseDTO dto = new ResponseDTO();
        dto.successFlag = false;
        dto.message = message;

        return dto;
    }
}
