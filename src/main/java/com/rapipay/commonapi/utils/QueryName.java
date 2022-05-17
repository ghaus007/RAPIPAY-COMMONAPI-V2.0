package com.rapipay.commonapi.utils;

public class QueryName {
    public static final String updateData = "update [glbConfig].[CONFIG_SETTING] set SETTING_VALUE =:settingValue where SETTING_NAME =:settingName";
    public static final String getAllData = "select SETTING_NAME, SETTING_VALUE,SETTING_TYPE from [glbConfig].[CONFIG_SETTING]";
    public static final String checkData = " select SETTING_NAME from [glbConfig].[CONFIG_SETTING] where SETTING_NAME=:settingName ";

    private QueryName() {
    }
}