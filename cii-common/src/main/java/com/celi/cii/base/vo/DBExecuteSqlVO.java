package com.celi.cii.base.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DBExecuteSqlVO {

    private String dbType;
    
    private String key;
    
    private String schemaName;
    
    private String script;
    
    private String sqlType;
}
