package com.telenor.greeting.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;

@ApiModel
public enum Type {

    SMALL,
    BIG;

    @JsonCreator
    public static Type fromString(String name) {
        return Type.valueOf(name.toUpperCase());
    }

    @JsonValue
    public String getValue() {
        return name().toLowerCase();
    }

}
