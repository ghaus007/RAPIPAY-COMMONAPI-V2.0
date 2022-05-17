package com.rapipay.commonapi.utils;
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//


public enum CommonApiEnum {
    SUCCESS_RESPONSE_CODE("200"),
    SUCCESS_RESPONSE_MESSAGE("success"),
    FAIL_RESPONSE_CODE("401"),
    SOMETHING_WENT_WRONG("Something Went Wrong"),
    SETTING_NAME_NOT_EXISTS_MESSAGE("Setting name doesnt exists in table"),
    FAILED_AUTHORIZATION("Authorization failed at Java layer");

    public final String value;

    private CommonApiEnum(String value) {
        this.value = value;
    }
}

