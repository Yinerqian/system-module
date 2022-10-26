package com.celi.system.cii.engine.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class FishBoneData implements Serializable {

    private String label;

    private List<FishBoneData> children;
}
