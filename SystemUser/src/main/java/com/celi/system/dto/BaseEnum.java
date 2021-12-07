package com.celi.system.dto;

import com.fasterxml.jackson.annotation.JsonValue;

public interface BaseEnum {
    @JsonValue
    String getCode();

    String getTitle();
}
