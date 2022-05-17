package com.rapipay.commonapi.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rapipay.commonapi.requestmodel.CommonApiAzureRequestDto;
import com.rapipay.commonapi.requestmodel.CommonApiMongoDBRequestDto;
import com.rapipay.commonapi.responsemodel.ApiResponseData;
import com.rapipay.commonapi.responsemodel.CommonApiResponseModal;
import com.rapipay.commonapi.commonapiservice.CommonApiService;
import com.rapipay.commonapi.utils.CommonApiEnum;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class CommonApiController {
    public static final Logger log = LogManager.getLogger(CommonApiController.class);

    public CommonApiController() {
    }

    @PostMapping({"/updateConfigSetting"})
    public CommonApiResponseModal postUpdateConfig(@RequestHeader("auth") String auth, @RequestHeader String urn, @RequestBody String requestDto) throws JsonProcessingException {
        log.info("[URN_{}] Inside Common Api controller with request dto {}", urn, new JSONObject(requestDto));
        CommonApiResponseModal commonApiResponseModal = new CommonApiResponseModal();
        commonApiResponseModal.setApiResponseData(new ApiResponseData());
        ObjectMapper objectMapper = new ObjectMapper();
        CommonApiMongoDBRequestDto commonApiMongoDBRequestDto = (CommonApiMongoDBRequestDto) objectMapper.readValue(requestDto, CommonApiMongoDBRequestDto.class);
        commonApiMongoDBRequestDto.setDate((new SimpleDateFormat("YYYY-MM-dd hh:mm:ss")).format(new Date()));
        JSONObject request = new JSONObject(requestDto);
        CommonApiAzureRequestDto commonApiAzureRequestDto = new CommonApiAzureRequestDto();
        commonApiAzureRequestDto.setSettingName(request.optString("settingName"));
        commonApiAzureRequestDto.setSettingValue(request.optString("settingValue"));

        try {
            log.info("[URN_{}] Calling method to navigate to Service layer {}", urn, new JSONObject(commonApiAzureRequestDto));
            commonApiResponseModal.setApiResponseCode(CommonApiEnum.SUCCESS_RESPONSE_CODE.value);
            commonApiResponseModal.setApiResponseMessage(CommonApiEnum.SUCCESS_RESPONSE_MESSAGE.value);
            this.commonApiService().main(auth, urn, commonApiMongoDBRequestDto, commonApiAzureRequestDto, commonApiResponseModal);
        } catch (NullPointerException e) {
            log.error("Exception while updating data in Common api", e.getMessage(), e);
            commonApiResponseModal.setApiResponseCode(CommonApiEnum.FAIL_RESPONSE_CODE.value);
            commonApiResponseModal.setApiResponseMessage(CommonApiEnum.SOMETHING_WENT_WRONG.value);
        } catch (Exception e) {
            log.error("Exception while updating data in Common api", e.getMessage(), e);
            commonApiResponseModal.setApiResponseCode(CommonApiEnum.FAIL_RESPONSE_CODE.value);
            commonApiResponseModal.setApiResponseMessage(CommonApiEnum.SOMETHING_WENT_WRONG.value);
        }

        commonApiResponseModal.setApiResponseFrom("Common Api");
        commonApiResponseModal.setApiResponseDateTime((new SimpleDateFormat("YYYY-MM-dd hh:mm:ss")).format(new Date()));
        log.info("[URN_{}] Response sent to node {}", urn, commonApiResponseModal);
        return commonApiResponseModal;
    }

    @GetMapping({"/getConfigSetting"})
    public CommonApiResponseModal getUpdateConfig(@RequestHeader("auth") String auth, @RequestHeader String urn) {
        log.info("[URN_{}] Inside Common Api controller ", urn);
        CommonApiResponseModal commonApiResponseModal = new CommonApiResponseModal();
        commonApiResponseModal.setApiResponseData(new ApiResponseData());

        try {
            log.info("[URN_{}] Calling method to navigate to service layer ", urn);
            this.commonApiService().getMain(auth, urn, commonApiResponseModal);
            commonApiResponseModal.setApiResponseCode(CommonApiEnum.SUCCESS_RESPONSE_CODE.value);
            commonApiResponseModal.setApiResponseMessage(CommonApiEnum.SUCCESS_RESPONSE_MESSAGE.value);
        } catch (NullPointerException var5) {
            log.error("Exception is", var5.getMessage(), var5);
            commonApiResponseModal.setApiResponseCode(CommonApiEnum.FAIL_RESPONSE_CODE.value);
            commonApiResponseModal.setApiResponseMessage(CommonApiEnum.SOMETHING_WENT_WRONG.value);
        } catch (Exception var6) {
            log.error("Exception is", var6.getMessage(), var6);
            commonApiResponseModal.setApiResponseCode(CommonApiEnum.FAIL_RESPONSE_CODE.value);
            commonApiResponseModal.setApiResponseMessage(CommonApiEnum.SOMETHING_WENT_WRONG.value);
        }

        commonApiResponseModal.setApiResponseFrom("Common Api");
        commonApiResponseModal.setApiResponseDateTime((new SimpleDateFormat("YYYY-MM-dd hh:mm:ss")).format(new Date()));
        log.info("[URN_{}] Response sent to node {}", urn, commonApiResponseModal);
        return commonApiResponseModal;
    }

    @Lookup
    public CommonApiService commonApiService() {
        return null;
    }
}
