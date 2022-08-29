package com.celi.system.cii.engine.entity;

import com.celi.cii.base.entity.BaseCreateBy;
import com.celi.system.cii.engine.entity.enums.BasicModeEnum;
import com.celi.system.cii.engine.entity.enums.ChValueModeEnum;
import com.celi.system.cii.engine.entity.enums.ChValueTypeEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: Ce-li
 * @Date: 2022/7/19 11:31
 * 质量重评记录
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QualityChValue extends BaseCreateBy {

    /**
     * 特征值id
     */
    private String chValueId;

    /**
     * 评价对象Id
     */
    private String targetId;

    /**
     * 产线id
     */
    private String lineId;

    /**
     * 产线Id集合
     */
    private String lineIds;

    private String lineName;

    /**
     * 开始信号uuid
     */
    private String startPointUuid;

    private String startPointName;

    /**
     * 结束信号uuid
     */
    private String endPointUuid;

    private String endPointName;

    /**
     * 特征值名称
     */
    private String chValueName;

    /**
     * 特征值编码
     */
    private String chValueCode;

    // 跟踪引擎标记的开始时间
    private Date startDate;

    // 跟踪引擎标记的结束时间
    private Date endDate;

    // 特征值关联的点位、别名列表
    private List<EvalPointConfig> evalPointConfigList;


    /**
     * 特征值类型，1-基本特征值 2-时空转换特征值
     * 默认为 1-基本特征值
     */
    private ChValueTypeEnum chValueType = ChValueTypeEnum.BASIC_FEATURES_VALUE;

    /**
     * 特征值模式，1-基本模式 2-高级模式
     */
    private ChValueModeEnum chValueMode;

    /**
     * 基本模式，avg-平均值，max-最大值，min-最小值，median 中位数 等
     */
    private BasicModeEnum basicMode;

    /**
     * 评价结果数据，json形式
     */
    private String advanceScript;

    /**
     * 序号
     */
    private Integer orderIndex;

    /**
     * 评价对象
     */
    private String targetName;


    /**
     * 特征值开始信号点位
     */
    private String startPointId;

    /**
     * 特征值结束信号点位
     */
    private String endPointId;

    // 特征值曲线的数组，形如[[0, 5.1], [10000, 4.9]]
    private List<List> resultArray = new ArrayList<>();

    @JsonProperty(value = "chValueTypeName")
    public String getChValueTypeName() {
        return this.chValueType != null ? this.chValueType.getTitle() : null ;
    }

    @JsonProperty(value = "chValueModelName")
    public String getChValueModelName() {
        return this.chValueMode != null ? this.chValueMode.getTitle() : null ;
    }

    @JsonProperty(value = "basicModeName")
    public String getBasicModeName() {
        return this.basicMode != null ? this.basicMode.getTitle() : null ;
    }

}
