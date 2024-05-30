package com.celi.system.enums;

public enum ServiceCode {
    SUCCESS(0, "success"),
    UNKNOWN_FAILURE(1, "服务内部接口调用异常"),
    UNKNOWN_USER(2, "请检查您的用户名或密码是否正确"),
    UNAUTHORIZED(3, "权限不足"),
    SAME_LOGIN_NAME_USER(4, "账号已存在"),
    SAME_PERMISSION_NAME(5, "权限名称重复"),
    SAME_ROLE_NAME(6, "角色名称重复"),
    SAME_ROLE_NAME_EN(7, "角色英文名称重复"),
    SAME_PERMISSION_CODE(8, "权限code重复"),
    BIZ_USER_MAPPING_NOT_EXIST(9, "业务系统用户映射关系不存在，请联系管理员"),
    NOT_SUPPORTED_SQL(100, "SQL语法错误或不支持的SQL类型"),
    ROLE_ALREADY_BIND(1000, "该角色已被分配给用户"),
    PERMISSION_ALREADY_BIND(1001, "该权限已被分配给角色"),
    SAME_DEPT_CODE(1002, "机构编号重复"),
    PARENT_DEPT_CANNOT_BE_SELF(2001, "父机构不能设置为自身"),
    PARENT_DEPT_CANNOT_BE_CHILD(2002, "不能将原有子机构设置为父机构"),
    DEPT_HAS_BIND_USER(2003, "该机构已绑定用户不可删除"),
    DEPT_HAS_CHILDREN(2004, "该机构存在子机构不可删除"),
    PERMISSION_HAS_CHILDREN(2005, "该权限存在子权限不可删除"),
    USER_DISABLED(40001, "该用户无法使用"),
    CHECK_INPUT_PASSWORD(4001, "请检查您的用户名或密码是否正确"),
    AUTHENTICATION_FAILED(401, "AUTHENTICATION FAILED"),

    ;

    private Integer code;

    private String message;

    ServiceCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
