package com.celi.cii.base.constant;

import com.celi.cii.base.entity.BaseEnum;
import lombok.Getter;

@Getter
public enum CiiDevicePermissionEnum implements BaseEnum {

    M_DEVICE_SPACE("M_DEVICE_SPACE", 0,"设备空间"),
    M_DEVICE_SPACE_ADD("M_DEVICE_SPACE_ADD", 0, "新增设备空间"),
    M_DEVICE_SPACE_EDIT("M_DEVICE_SPACE_EDIT", 0, "修改设备空间"),
    M_DEVICE_SPACE_VIEW("M_DEVICE_SPACE_VIEW", 0, "查看设备空间"),
    M_DEVICE_SPACE_DEL("M_DEVICE_SPACE_DEL", 0, "删除设备空间"),

    M_DEVICE_MODEL("M_DEVICE_MODEL", 0, "设备建模"),
    M_DEVICE_MODEL_ADD("M_DEVICE_MODEL_ADD", 0, "新增模型"),
    M_DEVICE_MODEL_EDIT("M_DEVICE_MODEL_EDIT", 0, "修改模型"),
    M_DEVICE_MODEL_VIEW("M_DEVICE_MODEL_VIEW", 0, "查看模型"),
    M_DEVICE_MODEL_DEL("M_DEVICE_MODEL_DEL", 0, "删除模型"),

    M_DEVICE_INFO("M_DEVICE_INFO", 0, "设备管理"),
    M_DEVICE_INFO_ADD("M_DEVICE_INFO_ADD", 0, "新增设备"),
    M_DEVICE_INFO_EDIT("M_DEVICE_INFO_EDIT", 0, "修改设备"),
    M_DEVICE_INFO_VIEW("M_DEVICE_INFO_VIEW", 0, "查看设备"),
    M_DEVICE_INFO_DEL("M_DEVICE_INFO_DEL", 0, "删除设备"),

    M_DEVICE_TYPE("M_DEVICE_TYPE", 0, "设备分类"),
    M_DEVICE_TYPE_ADD("M_DEVICE_TYPE_ADD", 0, "新增分类"),
    M_DEVICE_TYPE_EDIT("M_DEVICE_TYPE_EDIT", 0, "修改分类"),
    M_DEVICE_TYPE_VIEW("M_DEVICE_TYPE_VIEW", 0, "查看分类"),
    M_DEVICE_TYPE_DEL("M_DEVICE_TYPE_DEL", 0, "删除分类"),

    M_DEVICE_FACTORY("M_DEVICE_FACTORY", 0, "设备厂家"),
    M_DEVICE_FACTORY_ADD("M_DEVICE_FACTORY_ADD", 0, "新增厂家"),
    M_DEVICE_FACTORY_EDIT("M_DEVICE_FACTORY_EDIT", 0, "修改厂家"),
    M_DEVICE_FACTORY_VIEW("M_DEVICE_FACTORY_VIEW", 0, "查看厂家"),
    M_DEVICE_FACTORY_DEL("M_DEVICE_FACTORY_DEL", 0, "删除厂家"),

    M_DEVICE_BRAND("M_DEVICE_BRAND", 0, "设备品牌"),
    M_DEVICE_BRAND_ADD("M_DEVICE_BRAND_ADD", 0, "新增品牌"),
    M_DEVICE_BRAND_EDIT("M_DEVICE_BRAND_EDIT", 0, "修改品牌"),
    M_DEVICE_BRAND_VIEW("M_DEVICE_BRAND_VIEW", 0, "查看品牌"),
    M_DEVICE_BRAND_DEL("M_DEVICE_BRAND_DEL", 0, "删除品牌"),

    M_EVENT_RULE("M_EVENT_RULE", 0, "规则管理"),
    M_EVENT_RULE_ADD("M_EVENT_RULE_ADD", 0, "新增规则"),
    M_EVENT_RULE_EDIT("M_EVENT_RULE_EDIT", 0, "修改规则"),
    M_EVENT_RULE_VIEW("M_EVENT_RULE_VIEW", 0, "查看规则"),
    M_EVENT_RULE_DEL("M_EVENT_RULE_DEL", 0, "删除规则"),

    M_EVENT_MESSAGE("M_EVENT_MESSAGE", 0, "消息模板"),
    M_EVENT_MESSAGE_ADD("M_EVENT_MESSAGE_ADD", 0, "新增模板"),
    M_EVENT_MESSAGE_EDIT("M_EVENT_MESSAGE_EDIT", 0, "修改模板"),
    M_EVENT_MESSAGE_VIEW("M_EVENT_MESSAGE_VIEW", 0, "查看模板"),
    M_EVENT_MESSAGE_DEL("M_EVENT_MESSAGE_DEL", 0, "删除模板"),

    M_COM_GATEWAY("M_COM_GATEWAY", 0, "通信网关"),
    M_COM_GATEWAY_ADD("M_COM_GATEWAY_ADD", 0, "新增网关"),
    M_COM_GATEWAY_EDIT("M_COM_GATEWAY_EDIT", 0, "修改网关"),
    M_COM_GATEWAY_VIEW("M_COM_GATEWAY_VIEW", 0, "查看网关"),
    M_COM_GATEWAY_DEL("M_COM_GATEWAY_DEL", 0, "删除网关"),

    M_COM_PROTOCOL("M_COM_PROTOCOL", 0, "通信协议"),
    M_COM_PROTOCOL_ADD("M_COM_PROTOCOL_ADD", 0, "新增协议"),
    M_COM_PROTOCOL_EDIT("M_COM_PROTOCOL_EDIT", 0, "修改协议"),
    M_COM_PROTOCOL_VIEW("M_COM_PROTOCOL_VIEW", 0, "查看协议"),
    M_COM_PROTOCOL_DEL("M_COM_PROTOCOL_DEL", 0, "删除协议"),

    M_DEVICE_EVENT("M_DEVICE_EVENT", 0, "事件通知"),
    M_DEVICE_EVENT_ADD("M_DEVICE_EVENT_ADD", 0, "新增事件通知"),
    M_DEVICE_EVENT_EDIT("M_DEVICE_EVENT_EDIT", 0, "修改事件通知"),
    M_DEVICE_EVENT_VIEW("M_DEVICE_EVENT_VIEW", 0, "查看事件通知"),
    M_DEVICE_EVENT_DEL("M_DEVICE_EVENT_DEL", 0, "删除事件通知"),
    ;

    private String code;
    private Integer platformAdminFlag;
    private String desc;

    CiiDevicePermissionEnum(String code, Integer platformAdminFlag, String desc) {
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
