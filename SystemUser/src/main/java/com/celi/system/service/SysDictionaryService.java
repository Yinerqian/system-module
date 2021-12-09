package com.celi.system.service;

import cn.dev33.satoken.stp.StpUtil;
import com.celi.system.constant.SystemConstants;
import com.celi.system.dao.SysDictionaryRepository;
import com.celi.system.entity.SysDictionary;
import com.celi.system.utils.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.PermissionDeniedDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindException;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author jiangshengjun
 * @date 2021/12/9
 */
@Service
public class SysDictionaryService {

    private static final String ROOT_PARENT_ID = "0";

    @Resource
    private SysDictionaryRepository sysDictionaryRepository;

    public List<SysDictionary> queryAllDictionary() {
        // 查询字典目录
        List<SysDictionary> sysDictionaryList = sysDictionaryRepository.queryByIsLeaf(SystemConstants.NO);
        return formatDicTree(sysDictionaryList);
    }

    public List<SysDictionary> queryDicItemByParentId(String parentId) {
        if (StringUtils.isBlank(parentId)) {
            throw new IllegalArgumentException("parentId can't be null");
        }
        return sysDictionaryRepository.queryByParentIdAndIsLeaf(parentId, SystemConstants.YES);
    }

    public List<SysDictionary> queryDicItemByDicCode(String dicCode) {
        if (StringUtils.isBlank(dicCode)) {
            throw new IllegalArgumentException("DicCode can't be null");
        }
        return sysDictionaryRepository.queryByDcCodeAndIsLeaf(dicCode, SystemConstants.YES);
    }

    public void saveDictionary(SysDictionary sysDictionary) {
        sysDictionary.setDcId(UUID.randomUUID().toString());
        sysDictionary.setCreatedBy(StpUtil.getLoginIdAsString());
        sysDictionary.setCreatedTime(DateUtils.now());
        sysDictionary.setUpdatedBy(StpUtil.getLoginIdAsString());
        sysDictionary.setUpdatedTime(DateUtils.now());
        sysDictionaryRepository.save(sysDictionary);
    }

    public void saveDictionaryList(List<SysDictionary> sysDictionaryList) {
        sysDictionaryRepository.saveAll(sysDictionaryList);
    }

    public void updateDictionary(SysDictionary sysDictionary) {
        sysDictionary.setUpdatedBy(StpUtil.getLoginIdAsString());
        sysDictionary.setUpdatedTime(DateUtils.now());
        sysDictionaryRepository.save(sysDictionary);
    }

    public void deleteDictionary(String dicId) {
        Optional<SysDictionary> optional = sysDictionaryRepository.findById(dicId);
        if (optional.isPresent()) {
            SysDictionary sysDictionary = optional.get();
            // 判断是否有子集
            List<SysDictionary> childList = sysDictionaryRepository.queryByParentId(dicId);
            if (!CollectionUtils.isEmpty(childList)) {
                throw new RuntimeException("该数据存在子集，不允许删除");
            }
            sysDictionaryRepository.deleteById(dicId);
        }
    }

    public void deleteDictionaryBatch(List<String> dicIds) {
        List<SysDictionary> sysDictionaryList = sysDictionaryRepository.findAllById(dicIds);

        List<String> parentIds = sysDictionaryList.stream().map(SysDictionary::getDcId).collect(Collectors.toList());
        List<SysDictionary> childList = sysDictionaryRepository.findAllById(parentIds);
        Set<String> containChildNameList = childList.stream().map(SysDictionary::getDcName).collect(Collectors.toSet());

        if (!CollectionUtils.isEmpty(containChildNameList)) {
            StringBuffer sb = new StringBuffer("以下字典存在子集:");
            containChildNameList.forEach(s -> {
                sb.append(s);
                sb.append(";");
            });
            sb.append("删除失败");
            throw new RuntimeException(sb.toString());
        }
        sysDictionaryRepository.deleteAllById(dicIds);
    }

    private List<SysDictionary> formatDicTree(List<SysDictionary> sysDictionaryList) {
        // 筛选出最外层的字典
        Map<String, SysDictionary> dictionaryMap = sysDictionaryList.stream().filter(sysDictionary -> {
            return StringUtils.equals(sysDictionary.getParentId(), ROOT_PARENT_ID);
        }).collect(Collectors.toMap(SysDictionary::getDcId, a -> a));
        // 非跟节点
        Iterator<SysDictionary> iterator = sysDictionaryList.stream().filter(sysDictionary -> {
            return !StringUtils.equals(sysDictionary.getParentId(), ROOT_PARENT_ID);
        }).collect(Collectors.toList()).iterator();
        while (iterator.hasNext()) {
            SysDictionary sysDictionary = iterator.next();
            if (dictionaryMap.containsKey(sysDictionary.getParentId())) {
                // 第二层
                iterator.remove();
                sysDictionary.addAllChildren(getChildDic(iterator, sysDictionary.getDcId()));
                dictionaryMap.get(sysDictionary.getParentId()).addChildren(sysDictionary);
            }
        }
        return dictionaryMap.values().stream().collect(Collectors.toList());
    }

    private List<SysDictionary> getChildDic(Iterator<SysDictionary> iterator, String parentId) {
        List<SysDictionary> childList = new ArrayList<>();
        while (iterator.hasNext()) {
            SysDictionary sysDictionary = iterator.next();
            if (StringUtils.equals(parentId, sysDictionary.getParentId())) {
                sysDictionary.setChildren(getChildDic(iterator, sysDictionary.getDcId()));
                childList.add(sysDictionary);
                iterator.remove();
            }
        }
        return childList;
    }
}
