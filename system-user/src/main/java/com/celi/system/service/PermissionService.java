package com.celi.system.service;

import cn.hutool.core.map.MapUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.celi.system.dao.PermissionRepository;
import com.celi.system.entity.Permission;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class PermissionService {

    private PermissionRepository permissionRepository;

    @Autowired
    public PermissionService(PermissionRepository AlarmRepository) {
        this.permissionRepository = AlarmRepository;
    }

    public List<String> listPermissionsByPermissionList(List<Integer> list) {
        return permissionRepository.listPermissionsByPermissionList(list);
    }

    public List<Permission> listAllAppMenus(String appCode) {
        return permissionRepository.listAllAppMenus(appCode);
    }

    public List<Permission> listAppMenusByUserId(Integer userId, String appCode) {
        List<Map<String, Object>> maps = permissionRepository.listAppMenusByUserId(userId, appCode);
        List<Permission> target = new ArrayList<>(maps.size());
        // JPA 对于 join 的字段无法正常映射到实体类，这里使用 Map + JSON 进行转化
        maps.forEach(permission -> target.add(JSONUtil.parse(JSONUtil.toJsonStr(permission)).toBean(Permission.class)));
        return target;
    }
}
