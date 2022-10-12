package com.celi.system.permission.enity;

import com.celi.cii.base.entity.BaseCreateBy;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class SysAppUser extends BaseCreateBy {

    /**
     * 应用id
     */
    private Integer appId;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     *上次访问时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date lastVisitDate;


}
