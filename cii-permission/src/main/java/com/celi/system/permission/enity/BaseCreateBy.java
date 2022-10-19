package com.celi.system.permission.enity;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseCreateBy implements Serializable {

    public static final Integer DELETED = 1;
    public static final Integer UN_DELETED = 0;

    protected Integer tenantId;

    protected Integer delFlag;

    protected Integer createBy;

    protected String createByName;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    protected Date createDate;

    protected Integer updateBy;

    protected String updateByName;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    protected Date updateDate;

    protected String keyword;

    protected String filterStartDate;

    protected String filterEndDate;

    protected Integer version;

    protected String orderByName;

    protected String orderByDirection;

    @JsonProperty(value = "orderBy")
    public String getOrderBy() {
        return StrUtil.isNotBlank(this.orderByName) && StrUtil.isNotBlank(this.orderByDirection) ? (this.orderByName + " " +  this.orderByDirection) : "";
    }
}
