package com.celi.cii.base.constant;

import com.celi.cii.base.entity.BaseEnum;
import lombok.Getter;

@Getter
public enum CiiDataIntegrationEnum implements BaseEnum {

    M_TASK_NODE("M_TASK_NODE", 0,"节点管理"),
    OP_TASK_NODE_ADD("OP_TASK_NODE_ADD", 0, "新增任务节点"),
    OP_TASK_NODE_EDIT("OP_TASK_NODE_EDIT", 0, "编辑任务节点"),
    OP_TASK_NODE_VIEW("OP_TASK_NODE_VIEW", 0, "查看任务节点"),
    OP_TASK_NODE_DEL("OP_TASK_NODE_DEL", 0, "删除任务节点"),

    M_BIZ_SYSTEM("M_BIZ_SYSTEM", 0, "业务系统管理"),
    OP_BIZ_SYSTEM_ADD("OP_BIZ_SYSTEM_ADD", 0, "新增业务系统"),
    OP_BIZ_SYSTEM_EDIT("OP_BIZ_SYSTEM_EDIT", 0, "编辑业务系统"),
    OP_BIZ_SYSTEM_VIEW("OP_BIZ_SYSTEM_VIEW", 0, "查看业务系统"),
    OP_BIZ_SYSTEM_DEL("OP_BIZ_SYSTEM_DEL", 0, "删除业务系统"),

    M_DATA_SOURCE_GROUP("M_DATA_SOURCE_GROUP", 0, "数据源分组"),
    OP_DATA_SOURCE_GROUP_ADD("OP_DATA_SOURCE_GROUP_ADD", 0, "新增数据源分组"),
    OP_DATA_SOURCE_GROUP_EDIT("OP_DATA_SOURCE_GROUP_EDIT", 0, "编辑数据源分组"),
    OP_DATA_SOURCE_GROUP_VIEW("OP_DATA_SOURCE_GROUP_VIEW", 0, "查看数据源分组"),
    OP_DATA_SOURCE_GROUP_DEL("OP_DATA_SOURCE_GROUP_DEL", 0, "删除数据源分组"),

    M_DATA_SOURCE("M_DATA_SOURCE", 0, "数据源管理"),
    OP_DATA_SOURCE_ADD("OP_DATA_SOURCE_ADD", 0, "新增数据源"),
    OP_DATA_SOURCE_EDIT("OP_DATA_SOURCE_EDIT", 0, "编辑数据源"),
    OP_DATA_SOURCE_VIEW("OP_DATA_SOURCE_VIEW", 0, "查看数据源"),
    OP_DATA_SOURCE_DEL("OP_DATA_SOURCE_DEL", 0, "删除数据源"),

    M_DATA_SOURCE_USER_REL("M_DATA_SOURCE_USER_REL", 0, "数据源详情"),
    OP_DATA_SOURCE_USER_REL_VIEW("OP_DATA_SOURCE_USER_REL_VIEW", 0, "数据表预览"),

    M_DB_TASK("M_DB_TASK", 0, "数据库集成任务"),
    OP_DB_TASK_ADD("OP_DB_TASK_ADD", 0, "新增数据库集成任务"),
    OP_DB_TASK_EDIT("OP_DB_TASK_EDIT", 0, "编辑数据库集成任务"),
    OP_DB_TASK_VIEW("OP_DB_TASK_VIEW", 0, "查看数据库集成任务"),

    M_DB_TASK_LOG("M_DB_TASK_LOG", 0, "数据库任务日志"),
    OP_DB_TASK_LOG_VIEW("OP_DB_TASK_LOG_VIEW", 0, "查看数据库任务日志"),

    M_REST_TASK("M_REST_TASK", 0, "REST集成任务"),
    OP_REST_TASK_ADD("OP_REST_TASK_ADD", 0, "新增REST集成任务"),
    OP_REST_TASK_EDIT("OP_REST_TASK_EDIT", 0, "编辑REST集成任务"),
    OP_REST_TASK_VIEW("OP_REST_TASK_VIEW", 0, "查看REST集成任务"),

    M_REST_TASK_LOG("M_REST_TASK_LOG", 0, "REST任务日志"),
    OP_REST_TASK_LOG_VIEW("OP_REST_TASK_LOG_VIEW", 0, "查看REST任务日志"),
    ;

    private String code;
    private Integer platformAdminFlag;
    private String desc;

    CiiDataIntegrationEnum(String code, Integer platformAdminFlag, String desc) {
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
