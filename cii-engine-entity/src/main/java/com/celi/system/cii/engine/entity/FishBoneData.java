package com.celi.system.cii.engine.entity;

import lombok.Data;

import java.util.List;

@Data
public class FishBoneData {

    private String label;

    private List<FishBoneData> children;
}
