package com.celi.cii.base.utils;

import com.celi.cii.base.entity.BaseCreateBy;

import java.util.Date;

public class ServiceUtil {

    /**
     * 将插入的通用代码抽象
     *
     * @param record 通用实体类实例
     * @return 返回修改后的 record
     */
    public static BaseCreateBy insertRecord(BaseCreateBy record) {
        if (null == record.getDelFlag()) {
            record.setDelFlag(0);
        }
        Integer userId = ClientUtils.getIntCurrentUserId();
        Date now = DateUtils.now();
        record.setCreateBy(userId);
        record.setUpdateBy(userId);
        record.setCreateDate(now);
        record.setUpdateDate(now);
        if (ClientUtils.getCurrentTenantId() != null) {
            record.setTenantId(ClientUtils.getCurrentTenantId());
        }
        return record;
    }

    /**
     * 将更新的通用代码抽象
     *
     * @param record 通用实体类实例
     * @return 返回修改后的 record
     */
    public static BaseCreateBy updateRecord(BaseCreateBy record) {
        Integer userId = ClientUtils.getIntCurrentUserId();
        Date now = DateUtils.now();
        record.setUpdateBy(userId);
        record.setUpdateDate(now);
        return record;
    }

    /**
     * 设置排序条件：按照order_index升序排列
     *
     * @param record 通用实体类实例
     * @return 返回修改后的 record
     */
    public static BaseCreateBy setOrderByIndex(BaseCreateBy record) {
        record.setOrderByName("order_index");
        record.setOrderByDirection("ASC");
        return record;
    }

}
