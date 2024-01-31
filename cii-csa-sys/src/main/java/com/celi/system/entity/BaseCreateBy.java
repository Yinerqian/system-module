package com.celi.system.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import java.util.Date;

@Data
@MappedSuperclass
public class BaseCreateBy {

    public static final Integer DELETED = 1;
    public static final Integer UN_DELETED = 0;


    @Column(name = "CREATED_BY", columnDefinition = "varchar(32)")
    protected String createdBy;

    @Column(name = "CREATED_BY_NAME", columnDefinition = "varchar(200)")
    @Transient
    protected String createdByName;

    @Column(name = "CREATED_TIME")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createdTime;

    @Column(name = "UPDATED_BY", columnDefinition = "varchar(32)")
    protected String updatedBy;

    @Column(name = "UPDATED_BY_NAME", columnDefinition = "varchar(200)")
    @Transient
    protected String updatedByName;

    @Column(name = "UPDATED_TIME")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updatedTime;

    @Column(name = "KEYWORD")
    @Transient
    protected String keyword;

    @Column(name = "FILTER_START_DATE")
    @Transient
    protected String filterStartDate;

    @Column(name = "FILTER_END_DATE")
    @Transient
    protected String filterEndDate;

    @Column(name = "VERSION")
    @Transient
    protected Integer version;

    @Transient
    protected String orderByName;

    @Transient
    protected String orderByDirection;

    @JsonProperty(value = "orderBy")
    public String getOrderBy() {
        return StringUtils.isNotBlank(this.orderByName) && StringUtils.isNotBlank(this.orderByDirection) ? (this.orderByName + " " + this.orderByDirection) : "";
    }

    /**
     * 租户Id
     */
    @Transient
    @Column(name = "TENANT_ID")
    private Integer tenantId;

    /**
     * 0-未删除 1-删除
     */
    @Transient
    private Integer delFlag;

}
