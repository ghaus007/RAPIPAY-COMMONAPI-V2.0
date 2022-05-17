package com.rapipay.commonapi.requestmodel;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(
        name = "common_api"
)
public class CommonApiAzureRequestDto {
    @Id
    @JsonProperty("settingName")
    private String settingName;
    @JsonProperty("settingValue")
    private String settingValue;

    public CommonApiAzureRequestDto() {
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

    public String toString() {
        return "CommonApiAzureRequestDto{settingName='" + this.settingName + '\'' + ", settingValue='" + this.settingValue + '\'' + '}';
    }
}
