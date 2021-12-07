package com.celi.system.entity;

import com.celi.system.enums.GenderEnum;
import com.celi.system.enums.UserStatusEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @date 2021/11/10
 * @desc 用户管理
 */

@Entity
@Table(name = "jimu_sys_user")
@Data
@ToString
@Log4j2
public class User extends BaseCreateBy {

    /**
     *  用户id
     */
    @Id
    @Column(name = "USER_ID", updatable = false)
    private String userId;

    /**
     *  用户类型，0-管理员 1-普通用户 2-开发者
     */
    @Column(name = "USER_TYPE")
    private Integer userType;

    /**
     *  登录名，全局唯一
     */
    @Column(name = "LOGIN_NAME", unique = true)
    private String loginName;

    /**
     * 昵称
     */
    @Column(name = "NICK_NAME")
    private String nickName;

    /**
     * 密码
     */
    @Column(name = "PASSWORD")
    private String password;

    // 修改密码 旧密码
    @Transient
    private String oldPassword;

    // 修改密码 新密码
    @Transient
    private String newPassword;

    /**
     * 是否过期，1-过期 0-不过期
     */
    @Column(name = "EXPIRED")
    private Integer expired;

    /**
     * 是否禁用 1-禁用，0-启用
     */
    @Column(name = "DISABLED")
    private UserStatusEnum disabled;

    /**
     *  性别，1-男 2-女 3-未选择
     */
    @Column(name = "GENDER")
    private GenderEnum gender;

    /**
     * 身份证号，暂不用
     */
    @Column(name = "ID_CARD")
    private String idCard;

    /**
     *  联系方式
     */
    @Column(name = "PHONE")
    private String phone;

    /**
     *  邮箱
     */
    @Column(name = "EMAIL")
    private String email;

    /**
     *  是否未修改过初始密码
     * 初始密码flag，默认初始"ep@123456"
     */
    @Column(name = "INIT_PASSWORD_FLAG")
    private Integer initPasswordFlag;


    /**
     *  上次登录时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    @Column(name = "LAST_LOGIN_DATE")
    private Date lastLoginDate;

    /**
     *  是否能删除，默认1，可以删除
     */
    @Column(name = "CAN_DEL_FLAG")
    private Integer canDelFlag;

    /**
     *  在线状态: 1-在线，0 离线
     */
    @Column(name = "ONLINE_STATUS")
    private Integer onlineStatus;


    /**
     *  该用户拥有的权限
     */
    @Transient
    private List<Role> roleList;


    @Transient
    private List<PermissionGroup> userPermissions;


}
