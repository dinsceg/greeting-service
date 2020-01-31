package com.telenor.greeting.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;

@ApiModel
public enum Account {

    PERSONAL,
    BUSINESS;

    @JsonCreator
    public static Account fromString(String name) {
        return Account.valueOf(name.toUpperCase());
    }

    @JsonValue
    public String getValue() {
        return name().toLowerCase();
    }

}
