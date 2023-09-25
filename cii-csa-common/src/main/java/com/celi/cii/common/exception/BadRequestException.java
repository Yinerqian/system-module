package com.celi.cii.common.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author jiangshengjun
 * @date 2022/3/7
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BadRequestException extends RuntimeException {

    private String message;
}
