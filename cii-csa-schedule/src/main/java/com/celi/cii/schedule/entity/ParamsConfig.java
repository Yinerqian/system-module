package com.celi.cii.schedule.entity;

import lombok.Data;

/**
 * @author jiangshengjun
 * @date 2023/10/10
 */
@Data
public class ParamsConfig {

    /**
     * java.lang.String格式，只支持包装类
     */
    private String type;

    /**
     * 参数值
     */
    private Object value;

    /**
     * 参数索引
     */
    private Integer index;
}
