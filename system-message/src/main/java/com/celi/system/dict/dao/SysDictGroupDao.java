package com.celi.system.dict.dao;

import com.celi.system.dict.entity.SysDictGroup;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysDictGroupDao {

    int insertSysDictGroup(@Param("record") SysDictGroup record);

    int deleteSysDictGroup(@Param("record") SysDictGroup record);

    int updateSysDictGroup(@Param("record") SysDictGroup record);

    SysDictGroup getDictGroupById(@Param("dictId") String dictId);

    List<SysDictGroup> listDictGroup(@Param("filter") SysDictGroup filter);

    int validDictCode(@Param("dictCode") String dictCode, @Param("tenantId") Integer currentTenantId, @Param("dictId") String dictId);

    SysDictGroup getDictGroupByCode(@Param("dictCode") String dictCode);

}
