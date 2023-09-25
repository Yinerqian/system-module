package com.celi.cii.base.enums;

public enum RequestMethodEnum {

    METHOD_GET("get", "GET"),
    METHOD_POST("post", "POST"),
    METHOD_PUT("put", "PUT"),
    METHOD_DELETE("delete", "DELETE");

    private String code;
    private String desc;

    RequestMethodEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return desc;
    }
}
