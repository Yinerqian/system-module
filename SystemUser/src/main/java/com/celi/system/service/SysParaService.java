package com.celi.system.service;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.celi.system.dao.SysParaRepository;
import com.celi.system.dto.CiiPageRequest;
import com.celi.system.dto.PageInfo;
import com.celi.system.entity.SysPara;
import com.celi.system.exception.ServiceException;
import com.celi.system.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

/**
 *  系统参数业务层
 */
@Service
@Slf4j
public class SysParaService {

    @Autowired
    private SysParaRepository sysParaRepository;


    public PageInfo<SysPara> pageSysPara(int pageNum, int pageSize, String orderByName, String orderByDirection, String paraName, String paraValue) {
        Page<SysPara> result = null;
        String sort = null;
        if (StrUtil.isNotEmpty(orderByName) && StrUtil.isNotEmpty(orderByDirection)){
            sort = String.format("%s,%s", orderByName, orderByDirection);
        }
        if (StringUtils.isBlank(paraName) && StringUtils.isBlank(paraValue)){
            result = sysParaRepository.findAll(CiiPageRequest.formatPagination(pageNum, pageSize,sort));
        }else {
            result = sysParaRepository.findAllByParaNameContainingOrParaValueContaining(paraName, paraValue, CiiPageRequest.formatPagination(pageNum, pageSize,sort));
        }
        return new PageInfo(result);
    }

    public SysPara getSysPara(Integer paraId) {
        SysPara sysPara = sysParaRepository.findByParaId(paraId);
        if (sysPara == null) {
            throw new ServiceException("未找到该参数");
        }
        return sysPara;
    }

    public void insertSysPara(SysPara sysPara) {
        sysPara.setParaId(IdUtil.simpleUUID());
        sysPara.setCreatedBy(StpUtil.getLoginIdAsString());
        sysPara.setCreatedTime(DateUtils.now());

        try {
            sysParaRepository.save(sysPara);
        } catch (Exception e) {
            log.info("参数添加异常");
            if (e instanceof DuplicateKeyException) {
                throw new ServiceException("系统参数名称或者编码不能重复");
            }
        }
    }


    public void updateSysPara(SysPara sysPara) {
        sysPara.setUpdatedBy(StpUtil.getLoginIdAsString());
        sysPara.setUpdatedTime(DateUtils.now());
        try {
            sysParaRepository.save(sysPara);
        } catch (Exception e) {
            log.info("参数修改异常");
            if (e instanceof DuplicateKeyException) {
                throw new ServiceException("系统参数名称或者编码不能重复");
            }
        }
    }

    public void removeSysPara(Integer paraId) {
        sysParaRepository.deleteByParaId(paraId);
    }

}
