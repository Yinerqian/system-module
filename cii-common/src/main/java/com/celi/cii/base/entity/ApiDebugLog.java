package com.celi.cii.base.entity;

import com.celi.cii.base.enums.LogLevelEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 数据开放接口调试日志类
 */
@Data
public class ApiDebugLog extends BaseCreateBy {

    public static final String COLLECTION_NAME = "DO_API_CALL_LOG";

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

    @JsonProperty(value = "levelName")
    public String getLevelName() {
        return this.level == null ? "" : this.level.getTitle();
    }

    /**
     * 日志内容
     */
    private String content;

}
