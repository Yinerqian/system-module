package com.celi.cii.schedule.entity;

import com.celi.cii.common.entity.BaseJpaCreateBy;
import lombok.Data;

import javax.persistence.*;

/**
 * @author jiangshengjun
 * @date 2023/10/10
 */

@Data
@Table(name = "schedule_config")
@Entity
public class ScheduleConfig extends BaseJpaCreateBy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer jobId;

    private String beanName;

    private String methodName;

    private String methodParams;

    private String cornExpression;

    private String remark;

    private Integer status;

}
