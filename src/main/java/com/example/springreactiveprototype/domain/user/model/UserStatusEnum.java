package com.example.springreactiveprototype.domain.user.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum UserStatusEnum {
    @JsonProperty("ACTIVE")
    ACTIVE,
    @JsonProperty("INACTIVE")
    INACTIVE,
    @JsonProperty("DELETED")
    DELETED
}
