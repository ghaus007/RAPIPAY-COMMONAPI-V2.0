package com.rapipay.commonapi.merchantInfoservice.impl;

import com.rapipay.commonapi.merchantInfoservice.MerchantInfoService;
import com.rapipay.commonapi.repository.MerchantInfoResponse;
import com.rapipay.commonapi.responsemodel.CommonApiResponseModal;
import com.rapipay.commonapi.utils.Constants;
import com.rapipay.commonapi.utils.RedisUtil;
import com.rapipay.commonapi.utils.StatusCode;
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
public class MerchantInfoServiceImpl implements MerchantInfoService {

    public static final Logger log = LogManager.getLogger(MerchantInfoServiceImpl.class);

    MerchantInfoResponse merchantInfoResponse;

    @Autowired
    RedisUtil redisUtil;

    @Override
    public void merchantMain(String userId, String mid, String tid, String urn, CommonApiResponseModal CommonApiResponseModal) {
        log.info("[URN_{}] Calling the Procedure to get the merchant Details  ", urn);

        merchantInfoResponse = GetMerchantInfoResponseObject();

        callProcedureToGetData(userId, mid, tid, urn, CommonApiResponseModal);
        if (!(CommonApiResponseModal.getApiResponseCode().equals(StatusCode.SUCCESS.getValue)
                &&
                !CommonApiResponseModal.getApiResponseData().getResponseCode()
                        .equals(StatusCode.SOMETHING_WENT_WRONG.getValue)
        )) {
            log.info("[URN_{}] ACCESS ERROR  ", urn);
            return;
        }
        setRedisData(mid, tid, urn, CommonApiResponseModal);

    }

    @Override
    public void callProcedureToGetData(String userId, String mid, String tid, String urn, CommonApiResponseModal CommonApiResponseModal) {
        try {
            log.info("[URN_{}] Inside the call Procedure to get data  ", urn);

            merchantInfoResponse.getNodeData(userId, mid, tid, urn, CommonApiResponseModal);
        } catch (NullPointerException e) {
            log.error("Error while Accessing Merchant data from DB : {}", e.getMessage(), e);
            CommonApiResponseModal.getApiResponseData().setResponseCode(StatusCode.SOMETHING_WENT_WRONG.getValue);
            CommonApiResponseModal.getApiResponseData().setResponseMessage(StatusCode.SOMETHING_WENT_WRONG.message);

        } catch (Exception e) {
            log.error("Error while Accessing Merchant data from DB : {}", e.getMessage());
            CommonApiResponseModal.getApiResponseData().setResponseCode(StatusCode.SOMETHING_WENT_WRONG.getValue);
            CommonApiResponseModal.getApiResponseData().setResponseMessage(StatusCode.SOMETHING_WENT_WRONG.message);

        }

    }

    @Override
    public void setRedisData(String mid, String tid, String urn, CommonApiResponseModal CommonApiResponseModal) {

        try {
            setDataFromDB(mid, tid, CommonApiResponseModal, urn);
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

    public void setDataFromDB(String mid, String tid, CommonApiResponseModal CommonApiResponseModal, String urn) {
        try {

            log.info("[URN_{}] Registering the data in redis.", urn);
            JSONObject requestData = (JSONObject) CommonApiResponseModal.getApiResponseData().getResponseData();

            String key = Constants.MERCHANTKEY + mid + "_" + tid;

            String reqData = requestData.toString();


            if (Objects.nonNull(redisUtil.setValue(key,reqData))) {
                log.info("[URN_{}] Key : {} is registered in redis Successfully .", urn, key);
                CommonApiResponseModal.getApiResponseData().setResponseCode(StatusCode.SUCCESS.getValue);
                CommonApiResponseModal.getApiResponseData().setResponseMessage(StatusCode.SUCCESS.message);
                CommonApiResponseModal.getApiResponseData().setResponseData("{}");

            } else {
                log.error("[URN_{}] Key : {} not registered in redis .", urn, key);
                CommonApiResponseModal.getApiResponseData().setResponseCode(StatusCode.SOMETHING_WENT_WRONG.getValue);
                CommonApiResponseModal.getApiResponseData().setResponseMessage(StatusCode.SOMETHING_WENT_WRONG.message);

            }

        } catch (NullPointerException e) {

            CommonApiResponseModal.getApiResponseData().setResponseCode(StatusCode.SOMETHING_WENT_WRONG.getValue);
            CommonApiResponseModal.getApiResponseData().setResponseMessage(StatusCode.SOMETHING_WENT_WRONG.message);
            log.error("error occurred while inserting mobi data to DB {} {} ", e.getMessage(),
                    e);
        } catch (Exception e) {

            CommonApiResponseModal.getApiResponseData().setResponseCode(StatusCode.SOMETHING_WENT_WRONG.getValue);
            CommonApiResponseModal.getApiResponseData().setResponseMessage(StatusCode.SOMETHING_WENT_WRONG.message);
            log.error("error occurred while inserting mobi data to DB {} {}", e.getMessage(), e);
        }

        CommonApiResponseModal.getApiResponseData().setResponseCode("200");
        CommonApiResponseModal.getApiResponseData().setResponseMessage("Success");
    }

    @Lookup
    public MerchantInfoResponse GetMerchantInfoResponseObject() {
        return null;
    }

}
