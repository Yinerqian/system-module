package com.celi.system.cii.engine.entity;

import com.celi.cii.base.entity.BaseCreateBy;
import com.celi.system.cii.engine.vo.ProductionLineProcedureVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * 物料信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Material extends BaseCreateBy {

    /**
     * 物料 Id
     */
    private String materialId;

    /**
     * 物料号
     */
    private String materialCode;

    /**
     * 物料生产开始时间
     */
    private Date startTime;

    /**
     * 物料生产结束时间
     */
    private Date endTime;

    /**
     * 产线信息
     */
    private ProductionLine productionLine;

    /**
     * 工序列表
     */
    private List<ProductionLineProcedureVO> producerList;

    /**
     * 评价对象
     * TODO 需要修改数据类型为 评价对象实体类
     */
    private List<String> evaluateProps;

}
