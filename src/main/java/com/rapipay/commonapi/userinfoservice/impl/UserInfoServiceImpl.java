package com.rapipay.commonapi.userinfoservice.impl;

import com.rapipay.commonapi.repository.UserInfoResponse;
import com.rapipay.commonapi.responsemodel.CommonApiResponseModal;
import com.rapipay.commonapi.userinfoservice.UserInfoService;
import com.rapipay.commonapi.utils.CommonApiEnum;
import com.rapipay.commonapi.utils.Constants;
import com.rapipay.commonapi.utils.RedisUtil;
import com.rapipay.commonapi.utils.StatusCode;
import com.rapipay.commonapi.utils.ValidateAuth;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Scope("prototype")
public class UserInfoServiceImpl implements UserInfoService {

    public static final Logger log = LogManager.getLogger(UserInfoServiceImpl.class);

    UserInfoResponse userInfoResponse;

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    ValidateAuth validateAuth;

    @Override
    public void userMain(String userId,String urn, String auth, CommonApiResponseModal commonApiResponseModal) {
        log.info("[URN_{}] Calling the Procedure to get the user Details  ", urn);

        userInfoResponse = GetMerchantInfoResponseObject();

        validateRequest(urn,auth,commonApiResponseModal);
        if (commonApiResponseModal.getApiResponseData().getResponseCode().equals(CommonApiEnum.FAIL_RESPONSE_CODE.value)) {
            log.info("[URN_{}] AUTHORIZATION ERROR  ", urn);
            return;
        }

        callProcedureToGetData(userId, urn, commonApiResponseModal);
        if (!(commonApiResponseModal.getApiResponseCode().equals(StatusCode.SUCCESS.getValue)
                &&
                !commonApiResponseModal.getApiResponseData().getResponseCode()
                        .equals(StatusCode.SOMETHING_WENT_WRONG.getValue)
        )) {
            log.info("[URN_{}] Error in fetching data from the database ", urn);
            return;
        }
        setRedisData(userId, urn, commonApiResponseModal);

    }

    @Override
    public void validateRequest(String urn, String auth, CommonApiResponseModal CommonApiResponseModal) {
        try {
            log.info("[URN_{}] Inside the validate request method  ", urn);
            validateAuth.validateAuth(urn, auth, CommonApiResponseModal);
        } catch (NullPointerException e) {
            log.error("Error while Validating the request : {}", e.getMessage(), e);
            CommonApiResponseModal.getApiResponseData().setResponseCode(StatusCode.SOMETHING_WENT_WRONG.getValue);
            CommonApiResponseModal.getApiResponseData().setResponseMessage(StatusCode.SOMETHING_WENT_WRONG.message);
        } catch (Exception e) {
            log.error("Error while Validating the request : {}", e.getMessage());
            CommonApiResponseModal.getApiResponseData().setResponseCode(StatusCode.SOMETHING_WENT_WRONG.getValue);
            CommonApiResponseModal.getApiResponseData().setResponseMessage(StatusCode.SOMETHING_WENT_WRONG.message);
        }

    }

    @Override
    public void callProcedureToGetData(String userId, String urn, CommonApiResponseModal CommonApiResponseModal) {
        try {
            log.info("[URN_{}] Inside the call Procedure to get data  ", urn);

            userInfoResponse.getResponeData(userId,urn, CommonApiResponseModal);
        } catch (NullPointerException e) {
            log.error("Error while Accessing User data from DB : {}", e.getMessage(), e);
            CommonApiResponseModal.getApiResponseData().setResponseCode(StatusCode.SOMETHING_WENT_WRONG.getValue);
            CommonApiResponseModal.getApiResponseData().setResponseMessage(StatusCode.SOMETHING_WENT_WRONG.message);

        } catch (Exception e) {
            log.error("Error while Accessing User data from DB : {}", e.getMessage());
            CommonApiResponseModal.getApiResponseData().setResponseCode(StatusCode.SOMETHING_WENT_WRONG.getValue);
            CommonApiResponseModal.getApiResponseData().setResponseMessage(StatusCode.SOMETHING_WENT_WRONG.message);

        }

    }

    @Override
    public void setRedisData(String userId, String urn, CommonApiResponseModal CommonApiResponseModal) {

        try {
            setDataFromDB(userId,CommonApiResponseModal, urn);
        } catch (NullPointerException e) {
            log.error("[URN_{}] error occurred in service layer : {} {} ", urn, e.getMessage(), e);
            CommonApiResponseModal.getApiResponseData().setResponseCode(StatusCode.SOMETHING_WENT_WRONG.getValue);
            CommonApiResponseModal.getApiResponseData().setResponseMessage(StatusCode.SOMETHING_WENT_WRONG.message);


        } catch (Exception e) {
            log.error("[URN_{}] error occurred in service layer : {}", urn, e.getMessage());
            CommonApiResponseModal.getApiResponseData().setResponseCode(StatusCode.SOMETHING_WENT_WRONG.getValue);
            CommonApiResponseModal.getApiResponseData().setResponseMessage(StatusCode.SOMETHING_WENT_WRONG.message);

        }
    }

    @Override
    public void setDataFromDB(String userId, CommonApiResponseModal commonApiResponseModal, String urn) {
        try {

            log.info("[URN_{}] Registering the data in redis.", urn);
            JSONObject requestData = (JSONObject) commonApiResponseModal.getApiResponseData().getResponseData();

            String key = Constants.USERKEY + userId;

            String reqData = requestData.toString();

            if (Objects.nonNull(redisUtil.setValue(key,reqData))) {
                log.info("[URN_{}] Key : {} is registered in redis Successfully .", urn, key);
                commonApiResponseModal.getApiResponseData().setResponseCode(StatusCode.SUCCESS.getValue);
                commonApiResponseModal.getApiResponseData().setResponseMessage(StatusCode.SUCCESS.message);

            } else {
                log.error("[URN_{}] Key : {} not registered in redis .", urn, key);
                commonApiResponseModal.getApiResponseData().setResponseCode(StatusCode.SOMETHING_WENT_WRONG.getValue);
                commonApiResponseModal.getApiResponseData().setResponseMessage(StatusCode.SOMETHING_WENT_WRONG.message);

            }

        } catch (NullPointerException e) {

            commonApiResponseModal.getApiResponseData().setResponseCode(StatusCode.SOMETHING_WENT_WRONG.getValue);
            commonApiResponseModal.getApiResponseData().setResponseMessage(StatusCode.SOMETHING_WENT_WRONG.message);
            log.error("error occurred while inserting mobi data to DB {} {} ", e.getMessage(),
                    e);
        } catch (Exception e) {

            commonApiResponseModal.getApiResponseData().setResponseCode(StatusCode.SOMETHING_WENT_WRONG.getValue);
            commonApiResponseModal.getApiResponseData().setResponseMessage(StatusCode.SOMETHING_WENT_WRONG.message);
            log.error("error occurred while inserting mobi data to DB {} {}", e.getMessage(), e);
        }

        commonApiResponseModal.getApiResponseData().setResponseCode("200");
        commonApiResponseModal.getApiResponseData().setResponseMessage("Success");
    }

    @Lookup
    public UserInfoResponse GetMerchantInfoResponseObject() {
        return null;
    }

}
