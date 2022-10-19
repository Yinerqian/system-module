package com.celi.system.permission.enity;

import com.celi.system.permission.enums.SysUserStatusEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 *  用户实体类
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SysUser extends BaseCreateBy {

    /**
     *  用户id
     */
    private Integer userId;

    /**
     *  租户id
     */
    private Integer currentTenantId;

    private String tenantName;

    /**
     *  用户类型，0-管理员 1-普通用户 2-开发者
     */
    private Integer userType;

    /**
     *  登录名，全局唯一
     */
    private String loginName;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 密码
     */
    private String password;

    // 修改密码 旧密码
    private String oldPassword;

    // 修改密码 新密码
    private String newPassword;

    /**
     * 是否过期，1-过期 0-不过期
     */
    private Integer expired;

    /**
     * 是否禁用 1-禁用，0-启用
     */
    private SysUserStatusEnum disabled;

    /**
     *  性别，1-男 2-女 3-未选择
     */
    private Integer gender;

    /**
     * 身份证号，暂不用
     */
    private String idCard;

    /**
     *  联系方式
     */
    private String phone;

    /**
     *  邮箱
     */
    private String email;

    /**
     *  是否未修改过初始密码
     * 初始密码flag，默认初始"ep@123456"
     */
    private Integer initPasswordFlag;

    /**
     * 新增用户时，系统生成
     */
    private String masterKey;

    /**
     * 新增用户时，系统生成
     */
    private String secretKey;

    /**
     * 上次登录地址
     */
    private String lastLoginIp;

    /**
     *  登录ip是否发生变化
     */
    private Integer loginIpChangeFlag;

    /**
     *  上次登录时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date lastLoginDate;

    /**
     *  是否能删除，默认1，可以删除
     */
    private Integer canDelFlag;

    /**
     *  在线状态: 1-在线，0 离线
     */
    private Integer onlineStatus;

    /**
     *  上次登录失败时间
     */
    private Date lastLoginFailDate;

    /**
     *  连续登陆失败次数
     */
    private Integer loginFailCount = 0;

    /**
     *  当前第一次登录失败时间
     */
    private Date currentFirstLoginFailDate;

    /**
     *  是否锁定，0 否 1是
     */
    private Integer lockFlag = 0;

    /**
     * 列表页面查询关键字
     */
    private String keyword;

    /**
     *  该用户的机构
     */
    private SysDept dept;

    /**
     *  该用户拥有的权限
     */
    private List<SysRole> roleList;

    // 关联机构id
    private List<Integer> deptIdList;

    @JsonProperty(value = "disabledName")
    public String getDisabledName() {
        return this.disabled != null ? this.disabled.getDesc() : null;
    }

    @JsonIgnore
    public Boolean getIsPlatformAdmin() {
        return Integer.valueOf(0).equals(userType) && ConstantUtils.CELI_ADMIN.equals(loginName);
    }
}
