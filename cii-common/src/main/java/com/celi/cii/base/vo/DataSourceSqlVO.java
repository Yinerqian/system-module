package com.celi.cii.base.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataSourceSqlVO {

    private String dataSourceId;

    private String schemaName;

    private String script;

    private String sqlType;
}

