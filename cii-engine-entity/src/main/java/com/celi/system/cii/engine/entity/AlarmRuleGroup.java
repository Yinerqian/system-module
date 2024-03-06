package com.celi.system.cii.engine.entity;

import com.celi.cii.base.entity.BaseCreateBy;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author dw_min
 * @Date 2024/2/21
 * @Description 告警分组
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AlarmRuleGroup extends BaseCreateBy {

    /**
     * 告警分组ID
     */
    private String groupId;

    /**
     * 告警分组名称
     */
    private String groupName;

    /**
     * 备注
     */
    private String remark;

    /**
     * 图标
     */
    private String icon;

    /**
     * 排序
     */
    private Integer orderIndex;
}
