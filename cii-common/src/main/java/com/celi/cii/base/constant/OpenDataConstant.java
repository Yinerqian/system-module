package com.celi.cii.base.constant;

public class OpenDataConstant {

    public static final String OPEN_DATA_SELECT = "select";
    public static final String OPEN_DATA_UPSERT = "upsert";
    public static final String OPEN_DATA_DELETE = "delete";

    public static final String PAGE_NUM = "pageNum";
    public static final String PAGE_SIZE = "pageSize";


    /**
     * 接口状态标记，0-禁用 1-启用
     */
    public static final Integer OPEN_API_STATUS_DISABLE = 0;
    public static final Integer OPEN_API_STATUS_ENABLE = 1;

    /**
     * 接口状态标记，0-不必传 1-必传
     */
    public static final Integer OPEN_API_PARAM_NOT_MUST = 0;
    public static final Integer OPEN_API_PARAM_MUST = 1;

    /**
     * 输入SQL模式，0-高级脚本模式 1-SQL模式
     */
    public static final Integer OPEN_API_PATTERN_SENIOR = 0;
    public static final Integer OPEN_API_PATTERN_SQL = 1;

    public static final Integer OPEN_API_CALL_FAIL = 0;
    public static final Integer OPEN_API_CALL_SUCCESS = 1;
}
