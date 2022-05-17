package com.rapipay.commonapi.requestmodel;


import com.fasterxml.jackson.annotation.JsonProperty;

public class CommonApiMongoDBRequestDto {
    @JsonProperty("settingValue")
    private String settingValue;
    @JsonProperty("settingName")
    private String settingName;
    @JsonProperty("acqLong")
    private String acqLong;
    @JsonProperty("acqLat")
    private String acqLat;
    @JsonProperty("loggedUser")
    private String loggedUser;
    @JsonProperty("ip")
    private String ip;
    @JsonProperty
    private String date;

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAcqLong() {
        return this.acqLong;
    }

    public void setAcqLong(String acqLong) {
        this.acqLong = acqLong;
    }

    public CommonApiMongoDBRequestDto() {
    }

    public String getSettingValue() {
        return this.settingValue;
    }

    public void setSettingValue(String settingValue) {
        this.settingValue = settingValue;
    }

    public String getSettingName() {
        return this.settingName;
    }

    public void setSettingName(String settingName) {
        this.settingName = settingName;
    }

    public String getAcqLat() {
        return this.acqLat;
    }

    public void setAcqLat(String acqLat) {
        this.acqLat = acqLat;
    }

    public String getLoggedUser() {
        return this.loggedUser;
    }

    public void setLoggedUser(String loggedUser) {
        this.loggedUser = loggedUser;
    }

    public String getIp() {
        return this.ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
