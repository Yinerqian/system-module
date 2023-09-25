package com.celi.cii.common.entity;

import com.fasterxml.jackson.annotation.JsonValue;


public interface BaseEnum {
    @JsonValue
    String getCode();

    String getTitle();
}