package com.celi.system.dict.entity;

import com.celi.cii.base.entity.BaseCreateBy;
import lombok.Data;

@Data
public class MsgDeviceToken extends BaseCreateBy {

    /**
     * 主键
     */
    private String id;

    /**
     * 系统sys_user表中的用户ID
     */
    private Integer userId;

    /**
     * 设备标识符(app每次登录时需要告诉本系统)
     */
    private String deviceToken;

    /**
     * 备注
     */
    private String remark;

    /**
     * 软件ID
     */
    private String appId;

    /**
     * 第三方APP中对应的用户ID
     */
    private String appUserId;

    /**
     * 用户名
     */
    private String loginName;

}
