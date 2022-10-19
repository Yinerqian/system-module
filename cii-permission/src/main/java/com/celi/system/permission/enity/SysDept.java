package com.celi.system.permission.enity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class SysDept extends BaseCreateBy {

    private Integer tenantId;

    private Integer deptId;

    private String deptName;

    private String deptHCode;

    private Integer parentId;

    private Integer orderIndex;

    private String charger;

    private String phone;

    private String remark;

    private Date createDate;

    private Integer createBy;

    private Integer delFlag;

    private List<SysDept> children;

    // APP树数据
    @JsonProperty(value = "label")
    public String getTreeLabel() {
        return this.deptName;
    }

}
