package com.celi.system.dict.service;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.celi.cii.base.entity.BaseCreateBy;
import com.celi.cii.base.exception.ServiceException;
import com.celi.cii.base.utils.ClientUtils;
import com.celi.cii.base.utils.ServiceUtil;
import com.celi.system.dict.dao.SysDictGroupDao;
import com.celi.system.dict.dao.SysDictItemDao;
import com.celi.system.dict.entity.SysDictGroup;
import com.celi.system.dict.entity.SysDictItem;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
public class SysDictItemService {

    @Resource
    private SysDictItemDao sysDictItemDao;
    @Resource
    private SysDictGroupDao sysDictGroupDao;

    /**
     * 新增一个字典项
     * @param record
     * 入库前验证字典编码同一租户的唯一性
     */
    public void insertSysDictItem(SysDictItem record) {
        SysDictGroup group = sysDictGroupDao.getDictGroupById(record.getDictId());
        if (group == null || group.getDelFlag() == BaseCreateBy.DELETED) {
            throw new ServiceException("所选字典分组不存在,请刷新后继续尝试");
        }
        if (sysDictItemDao.validDictCode(record.getDictItemCode(), ClientUtils.getCurrentTenantId(), null) > 0) {
            throw new ServiceException("字典编码重复");
        }
        record.setDictItemId(IdUtil.simpleUUID());
        ServiceUtil.insertRecord(record);
        sysDictItemDao.insertSysDictItem(record);
    }

    /**
     * 删除字典项
     * @param record
     */
    public void deleteSysDictItem(SysDictItem record) {
        ServiceUtil.updateRecord(record);
        sysDictItemDao.deleteSysDictItem(record);
    }

    /**
     * 修改字典项
     * @param record
     * 修改时 需要保证字典编码唯一性
     */
    public void updateSysDictItem(SysDictItem record) {
        if (StrUtil.isBlank(record.getDictId())) {
            throw new ServiceException("未设置所属字典分组");
        }
        if (sysDictItemDao.validDictCode(record.getDictItemCode(), ClientUtils.getCurrentTenantId(), record.getDictItemId()) > 0) {
            throw new ServiceException("字典编码重复");
        }
        ServiceUtil.updateRecord(record);
        sysDictItemDao.updateSysDictItem(record);
    }

    /**
     * 根据字典ID获取字典信息
     * @param dictItemId
     * @return
     */
    public SysDictItem getSysDictItemById(String dictItemId) {
        return sysDictItemDao.getDictItemById(dictItemId);
    }

    /**
     * 分页获取字典项
     * @param pageNum
     * @param pageSize
     * @param filter
     * @return
     */
    public PageInfo pageSysDictItem(int pageNum, int pageSize, SysDictItem filter) {
        filter.setTenantId(ClientUtils.getCurrentTenantId());
        String orderBy = StrUtil.isBlank(filter.getOrderBy()) ? "dict_item_code" : filter.getOrderBy();
        PageHelper.startPage(pageNum, pageSize, orderBy);
        return new PageInfo(sysDictItemDao.listDictItem(filter));
    }

    /**
     * 根据字典分组编码获取对应的字典项
     * @param dictCode
     * @return
     */
    public List<SysDictItem> getSysDictItemByGroup(String dictCode,String remark){
        SysDictGroup group=sysDictGroupDao.getDictGroupByCode(dictCode);
        if (group == null || group.getDelFlag() == BaseCreateBy.DELETED) {
            return null;
        }
        else{
            String dictId= group.getDictId();
            return sysDictItemDao.getSysDictItemByGroup(dictId,remark);
        }
    }

}
