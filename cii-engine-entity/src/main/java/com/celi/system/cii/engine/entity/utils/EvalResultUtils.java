package com.celi.system.cii.engine.entity.utils;

import com.celi.system.cii.engine.entity.Material;
import com.celi.system.cii.engine.entity.enums.EvalResultStatusEnum;

public class EvalResultUtils {

    public static EvalResultStatusEnum getResult(Material material, Float score) {
        // 暂定为超过80才合格
        if (score != null) {
            return score >= 80 ? EvalResultStatusEnum.QUALIFIED : EvalResultStatusEnum.UN_QUALIFIED;
        }

        return null;
    }
}
