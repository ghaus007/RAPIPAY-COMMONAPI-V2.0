package com.rapipay.commonapi.commonapiservice.impl;
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import com.rapipay.commonapi.repository.GetDataFromAzure;
import com.rapipay.commonapi.repository.InsertUpdateDataToAzure;
import com.rapipay.commonapi.requestmodel.CommonApiAzureRequestDto;
import com.rapipay.commonapi.requestmodel.CommonApiMongoDBRequestDto;
import com.rapipay.commonapi.responsemodel.CommonApiResponseModal;
import com.rapipay.commonapi.commonapiservice.CommonApiService;
import com.rapipay.commonapi.utils.CommonApiEnum;
import com.rapipay.commonapi.utils.ValidateAuth;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@Scope("prototype")
public class CommonApiServiceImpl implements CommonApiService {
    public static final Logger log = LogManager.getLogger(CommonApiServiceImpl.class);

    ValidateAuth validateAuth;

    public void main(String auth, String urn, CommonApiMongoDBRequestDto commonApiMongoDBRequestDto, CommonApiAzureRequestDto commonApiAzureRequestDto, CommonApiResponseModal commonApiResponseModal) {
        log.info("[URN_{}] Inside Service layer with request dto {}", urn, new JSONObject(commonApiMongoDBRequestDto));

        validateAuth = GetValidateAuthObject();
        try {
            validateAuth.validateAuth(urn, auth, commonApiResponseModal);
            if (commonApiResponseModal.getApiResponseData().getResponseCode() == CommonApiEnum.FAIL_RESPONSE_CODE.value) {
                return;
            }

            this.checkIfExists(urn, commonApiAzureRequestDto, commonApiResponseModal);
            if (commonApiResponseModal.getApiResponseData().getResponseCode() == CommonApiEnum.FAIL_RESPONSE_CODE.value) {
                return;
            }

            this.callMethodToUpdateData(urn, commonApiAzureRequestDto, commonApiResponseModal);
        } catch (NullPointerException var7) {
            log.error("[URN_{}] Exception occur {}", urn, var7.getMessage(), var7);
            commonApiResponseModal.getApiResponseData().setResponseCode(CommonApiEnum.FAIL_RESPONSE_CODE.value);
            commonApiResponseModal.getApiResponseData().setResponseMessage(CommonApiEnum.SOMETHING_WENT_WRONG.value);
            commonApiResponseModal.getApiResponseData().setResponseData("{}");
        } catch (Exception var8) {
            log.error("[URN_{}] Exception occur {}", urn, var8.getMessage());
            commonApiResponseModal.getApiResponseData().setResponseCode(CommonApiEnum.FAIL_RESPONSE_CODE.value);
            commonApiResponseModal.getApiResponseData().setResponseMessage(CommonApiEnum.SOMETHING_WENT_WRONG.value);
            commonApiResponseModal.getApiResponseData().setResponseData("{}");
        }

    }

    public void checkIfExists(String urn, CommonApiAzureRequestDto commonApiAzureRequestDto, CommonApiResponseModal commonApiResponseModal) {
        try {
            log.info("[URN_{}] Checking if settingName: {} exists in table ", urn, commonApiAzureRequestDto.getSettingName());
            String settingName = commonApiAzureRequestDto.getSettingName();
            String name = this.insertDataToAzure().checkParameter(settingName);
            if (name == null) {
                log.error("[URN_{}] Parameter settingName: {} doesn't exists in table {} ", urn, name);
                commonApiResponseModal.getApiResponseData().setResponseCode(CommonApiEnum.FAIL_RESPONSE_CODE.value);
                commonApiResponseModal.getApiResponseData().setResponseMessage(CommonApiEnum.SETTING_NAME_NOT_EXISTS_MESSAGE.value);
                commonApiResponseModal.getApiResponseData().setResponseData("{}");
            }
        } catch (NullPointerException var6) {
            log.error("[URN_{}] Error at checking settingName: {} if exists in table {} ", urn, var6.getMessage(), var6);
            commonApiResponseModal.getApiResponseData().setResponseCode(CommonApiEnum.FAIL_RESPONSE_CODE.value);
            commonApiResponseModal.getApiResponseData().setResponseMessage(CommonApiEnum.SETTING_NAME_NOT_EXISTS_MESSAGE.value);
            commonApiResponseModal.getApiResponseData().setResponseData("{}");
        } catch (Exception var7) {
            log.error("[URN_{}] Error at checking settingName: {} if exists in table {} ", urn, var7.getMessage());
            commonApiResponseModal.getApiResponseData().setResponseCode(CommonApiEnum.FAIL_RESPONSE_CODE.value);
            commonApiResponseModal.getApiResponseData().setResponseMessage(CommonApiEnum.SETTING_NAME_NOT_EXISTS_MESSAGE.value);
            commonApiResponseModal.getApiResponseData().setResponseData("{}");
        }

    }

    public void callMethodToInsertDataInMongo(String urn, CommonApiMongoDBRequestDto commonApiMongoDBRequestDto, CommonApiResponseModal commonApiResponseModal) {
        try {
            log.info("[URN_{}] Inserting data in mongo {}", urn, new JSONObject(commonApiMongoDBRequestDto));
        } catch (NullPointerException var5) {
            log.error("[URN_{}] Exception occur while inserting data in mongo {}", urn, var5.getMessage(), var5);
            commonApiResponseModal.getApiResponseData().setResponseCode(CommonApiEnum.FAIL_RESPONSE_CODE.value);
            commonApiResponseModal.getApiResponseData().setResponseMessage(CommonApiEnum.SOMETHING_WENT_WRONG.value);
            commonApiResponseModal.getApiResponseData().setResponseData("{}");
        } catch (Exception var6) {
            log.error("[URN_{}] Exception occur while inserting data in mongo {}", urn, var6.getMessage());
            commonApiResponseModal.getApiResponseData().setResponseCode(CommonApiEnum.FAIL_RESPONSE_CODE.value);
            commonApiResponseModal.getApiResponseData().setResponseMessage(CommonApiEnum.SOMETHING_WENT_WRONG.value);
            commonApiResponseModal.getApiResponseData().setResponseData("{}");
        }

    }

    public void callMethodToUpdateData(String urn, CommonApiAzureRequestDto commonApiAzureRequestDto, CommonApiResponseModal commonApiResponseModal) {
        try {
            log.info("[URN_{}] Updating data in azure {}", urn, new JSONObject(commonApiAzureRequestDto));
            String settingValue = commonApiAzureRequestDto.getSettingValue();
            String settingName = commonApiAzureRequestDto.getSettingName();
            this.insertDataToAzure().updateData(settingValue, settingName);
            commonApiResponseModal.getApiResponseData().setResponseCode(CommonApiEnum.SUCCESS_RESPONSE_CODE.value);
            commonApiResponseModal.getApiResponseData().setResponseMessage(CommonApiEnum.SUCCESS_RESPONSE_MESSAGE.value);
            commonApiResponseModal.getApiResponseData().setResponseData("{}");
        } catch (NullPointerException var6) {
            log.error("[URN_{}] Exception occur while updating data in azure {} ", urn, var6.getMessage(), var6);
            commonApiResponseModal.getApiResponseData().setResponseCode(CommonApiEnum.FAIL_RESPONSE_CODE.value);
            commonApiResponseModal.getApiResponseData().setResponseMessage(CommonApiEnum.SOMETHING_WENT_WRONG.value);
            commonApiResponseModal.getApiResponseData().setResponseData("{}");
        } catch (Exception var7) {
            log.error("[URN_{}] Exception occur while updating data in azure {}", urn, var7.getMessage());
            commonApiResponseModal.getApiResponseData().setResponseCode(CommonApiEnum.FAIL_RESPONSE_CODE.value);
            commonApiResponseModal.getApiResponseData().setResponseMessage(CommonApiEnum.SOMETHING_WENT_WRONG.value);
            commonApiResponseModal.getApiResponseData().setResponseData("{}");
        }

    }



    public void getMain(String auth, String urn, CommonApiResponseModal commonApiResponseModal) {
        log.info("[URN_{}] Inside Service layer ", urn);

        validateAuth = GetValidateAuthObject();
        try {
            validateAuth.validateAuth(urn,auth,commonApiResponseModal);
            if (commonApiResponseModal.getApiResponseData().getResponseCode() == "401") {
                return;
            }

            this.callMethodToGetDataFromAzure(urn, commonApiResponseModal);
        } catch (NullPointerException var5) {
            log.error("[URN_{}] Exception at calling method to get data from azure {}", urn, var5.getMessage(), var5);
            commonApiResponseModal.getApiResponseData().setResponseCode(CommonApiEnum.FAIL_RESPONSE_CODE.value);
            commonApiResponseModal.getApiResponseData().setResponseMessage(CommonApiEnum.SOMETHING_WENT_WRONG.value);
            commonApiResponseModal.getApiResponseData().setResponseData("{}");
        } catch (Exception var6) {
            log.error("[URN_{}] Exception at calling method to get data from azure {}", urn, var6.getMessage());
            commonApiResponseModal.getApiResponseData().setResponseCode(CommonApiEnum.FAIL_RESPONSE_CODE.value);
            commonApiResponseModal.getApiResponseData().setResponseMessage(CommonApiEnum.SOMETHING_WENT_WRONG.value);
            commonApiResponseModal.getApiResponseData().setResponseData("{}");
        }

    }

    public void callMethodToGetDataFromAzure(String urn, CommonApiResponseModal commonApiResponseModal) {
        try {
            log.info("[URN_{}] Getting data from azure ", urn);
            ArrayList<CommonApiAzureRequestDto> list = this.getDataFromAzure().getData();
            commonApiResponseModal.getApiResponseData().setResponseCode(CommonApiEnum.SUCCESS_RESPONSE_CODE.value);
            commonApiResponseModal.getApiResponseData().setResponseMessage(CommonApiEnum.SUCCESS_RESPONSE_MESSAGE.value);
            commonApiResponseModal.getApiResponseData().setResponseData(list);
        } catch (NullPointerException var4) {
            log.error("[URN_{}] Exception at fetching data from azure {}", urn, var4.getMessage(), var4);
            commonApiResponseModal.getApiResponseData().setResponseCode(CommonApiEnum.FAIL_RESPONSE_CODE.value);
            commonApiResponseModal.getApiResponseData().setResponseMessage(CommonApiEnum.SOMETHING_WENT_WRONG.value);
            commonApiResponseModal.getApiResponseData().setResponseData("{}");
        } catch (Exception var5) {
            log.error("[URN_{}] Exception at fetching data from azure {}", urn, var5.getMessage());
            commonApiResponseModal.getApiResponseData().setResponseCode(CommonApiEnum.FAIL_RESPONSE_CODE.value);
            commonApiResponseModal.getApiResponseData().setResponseMessage(CommonApiEnum.SOMETHING_WENT_WRONG.value);
            commonApiResponseModal.getApiResponseData().setResponseData("{}");
        }
    }


    @Lookup
    public InsertUpdateDataToAzure insertDataToAzure() {
        return null;
    }

    @Lookup
    public GetDataFromAzure getDataFromAzure() {
        return null;
    }

    @Lookup
    public ValidateAuth GetValidateAuthObject() {
        return null;
    }
}
