package com.celi.cii.base.entity;

import com.fasterxml.jackson.annotation.JsonValue;

public interface BaseEnum {
    @JsonValue
    String getCode();

    String getTitle();
}