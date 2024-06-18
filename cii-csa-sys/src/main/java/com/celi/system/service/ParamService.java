package com.celi.system.service;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.celi.cii.common.dto.PageInfo;
import com.celi.cii.common.exception.ServiceException;
import com.celi.system.constant.SystemConstants;
import com.celi.system.dao.ParamRepository;
import com.celi.system.dto.CiiPageRequest;
import com.celi.system.entity.Param;
import com.celi.system.entity.Role;
import com.celi.system.entity.User;
import com.celi.system.utils.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

/**
 * @Author: xining
 * @Date: 2024/06/17 15:51
 * @Description 参数Service层
 */
@Service
public class ParamService {

    @Resource
    private ParamRepository paramRepository;

    /**
     * 新增参数
     * @param param
     * @return
     */
    public int saveParamInfo(Param param) {

        // 验证参数编码是否唯一
        List<Param> paramList = paramRepository.findByConfigCode(param.getConfigCode());
        if (paramList.size() > 0) {
            throw new ServiceException("参数编码已存在, 请输入新的参数编码");
        }
        param.setConfigId(IdUtil.simpleUUID());
        param.setCreatedBy(StpUtil.getLoginIdAsString());
        param.setCreatedTime(DateUtils.now());
        param.setUpdatedBy(StpUtil.getLoginIdAsString());
        param.setUpdatedTime(DateUtils.now());
        Param save = paramRepository.save(param);
        if (ObjectUtil.isNotNull(save)){
            return SystemConstants.SUCCESS;
        } else {
            return SystemConstants.ERROR;
        }
    }

    /**
     * 分页查询参数信息
     * @param pageNum
     * @param pageSize
     * @param orderByName
     * @param orderByDirection
     * @param configName
     * @return
     */
    public PageInfo<Param> queryByPage(int pageNum, int pageSize, String orderByName, String orderByDirection, String configName) {
        Page<Param> result = null;
        String sort = null;
        if (StrUtil.isNotEmpty(orderByName) && StrUtil.isNotEmpty(orderByDirection)){
            sort = String.format("%s,%s", orderByName, orderByDirection);
        }
        if (StringUtils.isBlank(configName)){
            result = paramRepository.findAll(CiiPageRequest.formatPagination(pageNum, pageSize,sort));
        }else {
            result = paramRepository.findAllByConfigNameContaining(configName ,CiiPageRequest.formatPagination(pageNum, pageSize,sort));
        }
        return new PageInfo(result);
    }

    @Transactional
    public void deleteByConfigId(String configId) {
        paramRepository.deleteByConfigId(configId);
    }

    @Transactional
    public int updateByConfigId(Param param){
        // 验证参数编码是否唯一
        Param tempParam = paramRepository.findAllByConfigId(param.getConfigId());
        if (!param.getConfigCode().equals(tempParam.getConfigCode())) {
            List<Param> paramList = paramRepository.findByConfigCode(param.getConfigCode());
            if (paramList.size() > 0) {
                throw new ServiceException("参数编码已存在, 请输入新的参数编码");
            }
        }
        param.setUpdatedTime(DateUtils.now());
        param.setUpdatedBy(StpUtil.getLoginIdAsString());
        Param save = paramRepository.save(param);
        if (save != null){
            return SystemConstants.SUCCESS;
        }else {
            return SystemConstants.ERROR;
        }
    }

    /**
     * 查询
     * @param configId
     * @return
     */
    public Param findByConfigId(String configId) {
        return paramRepository.findAllByConfigId(configId);
    }
}
