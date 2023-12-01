package com.celi.system.dict.dao;

import com.celi.system.dict.entity.SysDictItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysDictItemDao {

    int insertSysDictItem(@Param("record") SysDictItem record);

    int deleteSysDictItem(@Param("record") SysDictItem record);

    int updateSysDictItem(@Param("record") SysDictItem record);

    SysDictItem getDictItemById(@Param("dictItemId") String dictItemId);

    List<SysDictItem> listDictItem(@Param("filter") SysDictItem filter);

    int validIfHasItem(@Param("dictId") String dictId);

    int validDictCode(@Param("dictItemCode") String dictItemCode, @Param("tenantId") Integer currentTenantId, @Param("dictItemId") String dictItemId);

    List<SysDictItem> getSysDictItemByGroup(@Param("dictId") String dictId,@Param("remark") String remark);

}
