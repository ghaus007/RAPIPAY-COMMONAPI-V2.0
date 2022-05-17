package com.rapipay.commonapi.reportgenerationservice.impl;

import com.rapipay.commonapi.reportgenerationservice.ReportGenerationService;
import com.rapipay.commonapi.repository.MerchantInfoResponse;
import com.rapipay.commonapi.repository.ReportDataResponse;
import com.rapipay.commonapi.responsemodel.CommonApiResponseModal;
import com.rapipay.commonapi.utils.CommonApiEnum;
import com.rapipay.commonapi.utils.ValidateAuth;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Service;


@Service
public class ReportGenerationServiceImpl implements ReportGenerationService {
    public static final Logger log = LogManager.getLogger(ReportGenerationServiceImpl.class);

    ReportDataResponse reportDataResponse;

    ValidateAuth validateAuth;

    @Override
    public void reportMain(String urn,String auth,String mid, String tid, String fromDate, String toDate,String reqFrom, String reportType, CommonApiResponseModal commonApiResponseModal) {
        try{
            validateAuth = GetValidateAuthObject();
            validateAuth.validateAuth(urn,auth,commonApiResponseModal);

            if(commonApiResponseModal.getApiResponseData().getResponseCode().equals(CommonApiEnum.FAIL_RESPONSE_CODE.value)){
                return ;
            }

            String request = getRequestBody(urn, auth, mid, tid, fromDate, toDate, reqFrom, reportType, commonApiResponseModal);

            if(commonApiResponseModal.getApiResponseData().getResponseCode().equals(CommonApiEnum.FAIL_RESPONSE_CODE.value)){
                return ;
            }

            getReportData(urn,reportType, request, commonApiResponseModal);

            if(commonApiResponseModal.getApiResponseData().getResponseCode().equals(CommonApiEnum.FAIL_RESPONSE_CODE.value)){
                return ;
            }


        }catch (NullPointerException var4) {
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

    public String getRequestBody(String urn,String auth,String mid, String tid, String fromDate, String toDate,String reqFrom, String reportType, CommonApiResponseModal commonApiResponseModal){
        JSONObject jsonObject = new JSONObject();

        try{
            jsonObject.put("mid",mid);
            jsonObject.put("tid",tid);
            jsonObject.put("fromDate",fromDate);
            jsonObject.put("toDate",toDate);
            jsonObject.put("reqestFrom",reqFrom);

        }catch (NullPointerException var4) {
            log.error("[URN_{}] Exception at making jsonBody {}", urn, var4.getMessage(), var4);
            commonApiResponseModal.getApiResponseData().setResponseCode(CommonApiEnum.FAIL_RESPONSE_CODE.value);
            commonApiResponseModal.getApiResponseData().setResponseMessage(CommonApiEnum.SOMETHING_WENT_WRONG.value);
            commonApiResponseModal.getApiResponseData().setResponseData("{}");
        } catch (Exception var5) {
            log.error("[URN_{}] Exception at making jsonBody {}", urn, var5.getMessage());
            commonApiResponseModal.getApiResponseData().setResponseCode(CommonApiEnum.FAIL_RESPONSE_CODE.value);
            commonApiResponseModal.getApiResponseData().setResponseMessage(CommonApiEnum.SOMETHING_WENT_WRONG.value);
            commonApiResponseModal.getApiResponseData().setResponseData("{}");
        }

        return jsonObject.toString();
    }

    @Override
    public void getReportData(String urn,String reportType, String request, CommonApiResponseModal commonApiResponseModal){
        try{
            reportDataResponse = GetReportDataResponseObject();
            reportDataResponse.getReportData(urn, reportType, request,commonApiResponseModal);
        }catch (NullPointerException var4) {
            log.error("[URN_{}] Exception at fetching data from database {}", urn, var4.getMessage(), var4);
            commonApiResponseModal.getApiResponseData().setResponseCode(CommonApiEnum.FAIL_RESPONSE_CODE.value);
            commonApiResponseModal.getApiResponseData().setResponseMessage(CommonApiEnum.SOMETHING_WENT_WRONG.value);
            commonApiResponseModal.getApiResponseData().setResponseData("{}");
        } catch (Exception var5) {
            log.error("[URN_{}] Exception at fetching data from database {}", urn, var5.getMessage());
            commonApiResponseModal.getApiResponseData().setResponseCode(CommonApiEnum.FAIL_RESPONSE_CODE.value);
            commonApiResponseModal.getApiResponseData().setResponseMessage(CommonApiEnum.SOMETHING_WENT_WRONG.value);
            commonApiResponseModal.getApiResponseData().setResponseData("{}");
        }
    }

    @Lookup
    public ReportDataResponse GetReportDataResponseObject() {
        return null;
    }

    @Lookup
    public ValidateAuth GetValidateAuthObject() {
        return null;
    }
}
