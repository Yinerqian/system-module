package com.celi.cii.base.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author jiangshengjun
 * @date 2022/3/7
 * @Description 请求错误  参数错误等
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BadRequestException extends RuntimeException {

    private String message;
}
