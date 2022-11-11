package com.example.springreactiveprototype.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum ResultRemarksEnum {
    @JsonProperty("PASS")
    PASS,
    @JsonProperty("FAIL")
    FAIL
}
