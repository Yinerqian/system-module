package com.celi.system.permission.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public interface BaseEnum {
    @JsonValue
    String getCode();

    String getTitle();
}
