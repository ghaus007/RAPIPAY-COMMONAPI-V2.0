package com.rapipay.commonapi.responsemodel;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class CommonApiResponseModal {
    private String apiResponseCode;
    private String apiResponseMessage;
    private ApiResponseData apiResponseData;
    private String apiResponseFrom;
    private String apiResponseDateTime;

    public CommonApiResponseModal() {
    }

    public String getApiResponseCode() {
        return this.apiResponseCode;
    }

    public void setApiResponseCode(String apiResponseCode) {
        this.apiResponseCode = apiResponseCode;
    }

    public String getApiResponseMessage() {
        return this.apiResponseMessage;
    }

    public void setApiResponseMessage(String apiResponseMessage) {
        this.apiResponseMessage = apiResponseMessage;
    }

    public ApiResponseData getApiResponseData() {
        return this.apiResponseData;
    }

    public void setApiResponseData(ApiResponseData apiResponseData) {
        this.apiResponseData = apiResponseData;
    }

    public String getApiResponseFrom() {
        return this.apiResponseFrom;
    }

    public void setApiResponseFrom(String apiResponseFrom) {
        this.apiResponseFrom = apiResponseFrom;
    }

    public String getApiResponseDateTime() {
        return this.apiResponseDateTime;
    }

    public void setApiResponseDateTime(String apiResponseDateTime) {
        this.apiResponseDateTime = apiResponseDateTime;
    }

    public String toString() {
        return "CommonApiResponseModal{apiResponseCode='" + this.apiResponseCode + '\'' + ", apiResponseMessage='" + this.apiResponseMessage + '\'' + ", apiResponseData=" + this.apiResponseData + ", apiResponseFrom='" + this.apiResponseFrom + '\'' + ", apiResponseDateTime='" + this.apiResponseDateTime + '\'' + '}';
    }
}
