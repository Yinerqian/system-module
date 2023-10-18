package com.celi.system.dto;

import com.celi.system.entity.PermissionGroup;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PermissionGroupDTO extends PermissionGroup {

    private List<PermissionGroupDTO> children = new ArrayList<>();
}
