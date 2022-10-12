package com.celi.system.permission.enity;

import com.celi.cii.base.entity.BaseCreateBy;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 角色类
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SysRole extends BaseCreateBy {

    public SysRole(Integer roleId, Integer tenantId) {
        this.roleId = roleId;
        this.tenantId = tenantId;
    }

    /**
     * 角色id
     */
    private Integer roleId;

    /**
     *  租户id
     */
    private Integer tenantId;

    private String tenantName;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色编码
     */
    private String roleCode;

    /**
     * 备注
     */
    private String remark;

    /**
     *  角色下有多个应用
     */
    @Deprecated
    private List<SysApp> appList;

    /**
     *  前端添加角色的时候 可以新增用户
     */
    private List<SysUser> userList;

    /**
     *  角色下的应用数量
     */
    private Integer appCount;

    /**
     *  角色下有多个应用
     */
    private List<SysApp> convertGroupList;

    /**
     * 用户管理关联角色 管理员选择的用户id
     */
    private Integer currentUserId;
}
