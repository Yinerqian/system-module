package com.celi.cii.base.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DBExecuteSqlVO {

    private String dbType;
    
    private String key;
    
    private String schemaName;
    
    private String script;
    
    private String sqlType;

    private Map<String, String> data;

    private List<Map<String, String>> dataList;

    private boolean ignoreNull;
}
