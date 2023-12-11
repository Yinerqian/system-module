package com.celi.system.dict.service;

import cn.hutool.core.util.IdUtil;
import com.celi.cii.base.exception.ServiceException;
import com.celi.cii.base.utils.ClientUtils;
import com.celi.cii.base.utils.ServiceUtil;
import com.celi.system.dict.dao.SysDictGroupDao;
import com.celi.system.dict.dao.SysDictItemDao;
import com.celi.system.dict.entity.SysDictGroup;
import com.celi.system.dict.entity.SysDictItem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lianghh
 * @date 2023/8/21 13:34
 * @desc
 **/
@Service
@Slf4j
public class SysDictGroupService {

    @Resource
    private SysDictGroupDao sysDictGroupDao;
    @Resource
    private SysDictItemDao sysDictItemDao;

    /**
     * 新增一个字典分组
     * @param record
     * 同租户不可重复
     */
    public void insertSysDictGroup(SysDictGroup record) {
        // 查看该租户下 是否存在同dictCode的记录
        if (sysDictGroupDao.validDictCode(record.getDictCode(), ClientUtils.getCurrentTenantId(), null) > 0) {
            throw new ServiceException("字典分组编码重复");
        }
        record.setDictId(IdUtil.simpleUUID());
        ServiceUtil.insertRecord(record);
        sysDictGroupDao.insertSysDictGroup(record);
    }

    /**
     * 删除一个字典分组
     * @param record
     * 若该分组下有具体的字典项 不允许删除
     */
    public void deleteSysDictGroup(SysDictGroup record) {
        // 检查该字典分组下 是否还有字典项
        if (sysDictItemDao.validIfHasItem(record.getDictId()) > 0) {
            throw new ServiceException("该分组下存在字典项,无法删除");
        }
        ServiceUtil.updateRecord(record);
        sysDictGroupDao.deleteSysDictGroup(record);
    }

    /**
     * 修改字典分组
     * @param record
     * 修改时也需要验证dictCode的唯一性
     */
    public void updateSysDictGroup(SysDictGroup record) {
        if (sysDictGroupDao.validDictCode(record.getDictCode(), ClientUtils.getCurrentTenantId(), record.getDictId()) > 0) {
            throw new ServiceException("字典分组编码重复");
        }
        ServiceUtil.updateRecord(record);
        sysDictGroupDao.updateSysDictGroup(record);
    }

    /**
     * 获取字典分组详情
     * @param dictId
     * @return
     */
    public SysDictGroup getSysDictGroupById(String dictId) {
        return sysDictGroupDao.getDictGroupById(dictId);
    }

    /**
     * 获取字典分组列表
     * @param filter
     * @return
     */
    public List<SysDictGroup> listSysDictGroup(SysDictGroup filter) {
        filter.setTenantId(ClientUtils.getCurrentTenantId());
        return sysDictGroupDao.listDictGroup(filter);
    }

}
