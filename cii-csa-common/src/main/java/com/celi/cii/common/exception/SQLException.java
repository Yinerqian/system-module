package com.celi.cii.common.exception;

/**
 * @author jiangshengjun
 * @date 2022/5/24
 * @desc 数据集成SQL执行错误异常
 */
public class SQLException extends RuntimeException {

    public SQLException(String message) {
        super(message);
    }


}
