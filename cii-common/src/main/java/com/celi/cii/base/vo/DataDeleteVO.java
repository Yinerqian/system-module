package com.celi.cii.base.vo;


import lombok.Data;

import java.util.List;

@Data
public class DataDeleteVO {
    /**
     * 数据源标识
     */
    private String key;

    private String dataSourceName;

    private String schemaName;

    private String tableName;

    private String id;

    private List<String> idList;

    private String logicField;

}
