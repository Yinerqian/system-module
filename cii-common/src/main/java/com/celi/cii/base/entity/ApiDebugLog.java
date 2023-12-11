package com.celi.cii.base.entity;

import com.celi.cii.base.enums.LogLevelEnum;
import lombok.Data;

/**
 * 数据开放接口调试日志类
 */
@Data
public class ApiDebugLog extends BaseCreateBy {

    /**
     * 接口 Id
     */
    private String apiId;

    /**
     * 接口名称，因为两个库无法join，需要额外存
     */
    private String apiName;

    /**
     * 日志等级
     */
    private LogLevelEnum level;

    /**
     * 日志内容
     */
    private String content;

}
