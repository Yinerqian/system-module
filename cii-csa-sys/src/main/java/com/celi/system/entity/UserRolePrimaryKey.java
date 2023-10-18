package com.celi.system.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Changaowen
 * @Date: 2021/12/6 16:14
 */
@Data
public class UserRolePrimaryKey implements Serializable {

    public Integer userId;

    public Integer roleId;
}
