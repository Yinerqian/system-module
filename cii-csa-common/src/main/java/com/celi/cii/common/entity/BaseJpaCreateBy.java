package com.celi.cii.common.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import java.util.Date;

@Data
@MappedSuperclass
public class BaseJpaCreateBy {

    /**
     * 删除
     * 正常状态
     */
    public static final Integer DELETED = 1;
    /**
     * 未删除
     * 异常状态
     */
    public static final Integer UN_DELETED = 0;

    @Column(name = "CREATE_BY", columnDefinition = "varchar(32)")
    protected Integer createBy;

    @Column(name = "CREATE_DATE")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createDate;

    @Column(name = "UPDATE_BY", columnDefinition = "varchar(32)")
    protected Integer updateBy;

    @Column(name = "UPDATE_DATE")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateDate;
    /**
     * 0-未删除 1-删除
     */
    @Transient
    private Integer delFlag;

}
