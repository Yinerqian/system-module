package com.celi.cii.base.constant;

import com.celi.cii.base.entity.BaseEnum;
import lombok.Getter;

@Getter
public enum CiiReportPermissionEnum implements BaseEnum {

    M_DATASOURCE("M_DATASOURCE", 1,"数据源"),
    M_DATASOURCE_VIEW("M_DATASOURCE_VIEW", 1, "查看数据源"),
    M_FORM_GROUP("M_FORM_GROUP", 0, "表单分组"),
    M_FORM_GROUP_ADD("M_FORM_GROUP_ADD", 0, "新增分组"),
    M_FORM_GROUP_EDIT("M_FORM_GROUP_EDIT", 0, "编辑分组"),
    M_FORM_GROUP_VIEW("M_FORM_GROUP_VIEW", 0, "查看分组"),
    M_FORM_GROUP_DEL("M_FORM_GROUP_DEL", 0, "删除分组"),
    M_FORM_DESIGN("M_FORM_DESIGN", 1, "表单设计"),
    M_FORM_DESIGN_ADD("M_FORM_DESIGN_ADD", 1, "新增应用"),
    M_FORM_DESIGN_EDIT("M_FORM_DESIGN_EDIT", 1, "编辑应用"),
    M_FORM_DESIGN_VIEW("M_FORM_DESIGN_VIEW", 1, "查看应用"),
    M_FORM_DESIGN_DEL("M_FORM_DESIGN_DEL", 1, "删除应用"),
    M_FORM_PLAN("M_FORM_PLAN", 1, "表单计划"),
    M_FORM_PLAN_ADD("M_FORM_PLAN_ADD", 1, "新增计划"),
    M_FORM_PLAN_EDIT("M_FORM_PLAN_EDIT", 1, "编辑计划"),
    M_FORM_PLAN_VIEW("M_FORM_PLAN_VIEW", 1, "查看计划"),
    M_FORM_PLAN_DEL("M_FORM_PLAN_DEL", 1, "删除计划"),
    M_PLAN_ORDER("M_PLAN_ORDER", 0, "计划工单"),
    M_PLAN_ORDER_ADD("M_PLAN_ORDER_ADD", 0, "新增角色"),
    M_PLAN_ORDER_EDIT("M_PLAN_ORDER_EDIT", 0, "编辑角色"),
    M_PLAN_ORDER_VIEW("M_PLAN_ORDER_VIEW", 0, "查看角色"),
    M_PLAN_ORDER_DEL("M_PLAN_ORDER_DEL", 0, "删除角色"),
    M_FORM_DATA("M_FORM_DATA", 0, "表单数据"),
    M_FORM_DATA_VIEW("M_FORM_DATA_VIEW", 0, "查看表单数据"),
    ;

    private String code;
    private Integer platformAdminFlag;
    private String desc;

    CiiReportPermissionEnum(String code, Integer platformAdminFlag, String desc) {
        this.code = code;
        this.platformAdminFlag = platformAdminFlag;
        this.desc = desc;
    }

    @Override
    public String getTitle() {
        return desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getPlatformAdminFlag() {
        return platformAdminFlag;
    }

    public void setPlatformAdminFlag(Integer platformAdminFlag) {
        this.platformAdminFlag = platformAdminFlag;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
