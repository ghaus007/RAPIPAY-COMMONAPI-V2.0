package com.rapipay.commonapi.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.rapipay.commonapi.merchantInfoservice.MerchantInfoService;
import com.rapipay.commonapi.merchantInfoservice.impl.MerchantInfoServiceImpl;
import com.rapipay.commonapi.responsemodel.ApiResponseData;
import com.rapipay.commonapi.responsemodel.CommonApiResponseModal;
import com.rapipay.commonapi.userinfoservice.UserInfoService;
import com.rapipay.commonapi.userinfoservice.impl.UserInfoServiceImpl;
import com.rapipay.commonapi.utils.Constants;
import com.rapipay.commonapi.utils.StatusCode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class UserAndMerchantController {

    MerchantInfoService mobiToNode;

    UserInfoService userInfoService;

    public static final Logger log = LogManager.getLogger(UserAndMerchantController.class);

    @GetMapping(value = "/getMerchantInfo")
    public CommonApiResponseModal getRedisData(@RequestHeader String userid,@RequestHeader String mid,@RequestHeader String tid,@RequestHeader String urn) {
        mobiToNode = getMobiToNodeObject();
        CommonApiResponseModal response = new CommonApiResponseModal();
        ApiResponseData res = new ApiResponseData();
        response.setApiResponseData(res);
        try {
            response.setApiResponseCode(StatusCode.SUCCESS.getValue);
            response.setApiResponseMessage(StatusCode.SUCCESS.message);
            response.getApiResponseData().setResponseCode("");
            response.getApiResponseData().setResponseMessage("");
            log.info("[URN_{}] Inside Merchant Info " +
                    " {}", mid,tid);
            mobiToNode.merchantMain(userid,mid,tid,urn,response);

        } catch (NullPointerException e) {
            log.error("Error in Setting the Redis key for Node >>>>{} , {} ", e.getMessage(), e);
            response.setApiResponseCode(StatusCode.SOMETHING_WENT_WRONG.getValue);
            response.setApiResponseMessage(StatusCode.SOMETHING_WENT_WRONG.message);
        } catch (Exception e) {
            log.error("Error in Setting the Redis key for Node >>>>{}", e.getMessage());
            response.setApiResponseCode(StatusCode.SOMETHING_WENT_WRONG.getValue);
            response.setApiResponseMessage(StatusCode.SOMETHING_WENT_WRONG.message);
        }

//        response.getApiResponseData().setResponseData("{}");
        response.setApiResponseDateTime(new SimpleDateFormat(Constants.RESPONSEDATEFORMAT).format(new Date()));
        response.setApiResponseFrom("GETMERCHANTINFO");
        log.info("[URN_{}]Response sent to MOBIPOS API calling : {}", response.toString());
        return response;

    }


    @GetMapping(value = "/getUserInfo")
    public CommonApiResponseModal getRedisData(@RequestHeader String userId, @RequestHeader String auth, @RequestHeader String urn) {
        userInfoService = getUserInfoServiceObject();
        CommonApiResponseModal response = new CommonApiResponseModal();
        ApiResponseData res = new ApiResponseData();
        response.setApiResponseData(res);
        try {
            response.setApiResponseCode(StatusCode.SUCCESS.getValue);
            response.setApiResponseMessage(StatusCode.SUCCESS.message);
            response.getApiResponseData().setResponseCode("");
            response.getApiResponseData().setResponseMessage("");
            log.info("[URN_{}] Inside User Info " +
                    " {}", urn,userId);
            userInfoService.userMain(userId,urn,auth,response);

        } catch (NullPointerException e) {
            log.error("Error in Setting the Redis key for Node >>>>{} , {} ", e.getMessage(), e);
            response.setApiResponseCode(StatusCode.SOMETHING_WENT_WRONG.getValue);
            response.setApiResponseMessage(StatusCode.SOMETHING_WENT_WRONG.message);
        } catch (Exception e) {
            log.error("Error in Setting the Redis key for Node >>>>{}", e.getMessage());
            response.setApiResponseCode(StatusCode.SOMETHING_WENT_WRONG.getValue);
            response.setApiResponseMessage(StatusCode.SOMETHING_WENT_WRONG.message);
        }

        response.getApiResponseData().setResponseData("{}");
        response.setApiResponseDateTime(new SimpleDateFormat(Constants.RESPONSEDATEFORMAT).format(new Date()));
        response.setApiResponseFrom("GETUSERINFO");
        return response;

    }



    @Lookup
    public MerchantInfoServiceImpl getMobiToNodeObject() {
        return null;
    }

    @Lookup
    public UserInfoServiceImpl getUserInfoServiceObject() {
        return null;
    }
}
