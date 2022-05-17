package com.rapipay.commonapi.repository;

import com.rapipay.commonapi.responsemodel.CommonApiResponseModal;
import com.rapipay.commonapi.responsemodel.CommonApiResponseModal;
import com.rapipay.commonapi.utils.ProcedureName;
import com.rapipay.commonapi.utils.StatusCode;
import com.rapipay.tsql.proc.AccessProcedureEnum;
import com.rapipay.tsql.proc.AccessStoredProcedureNew;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Repository
@Scope("prototype")
public class MerchantInfoResponse {
    public static final Logger log = LogManager.getLogger(MerchantInfoResponse.class);

    public void getNodeData(String userid,String mid,String tid,String urn,CommonApiResponseModal responseModal) {
        try {

            JSONObject dbResponseJson = new JSONObject();

            AccessStoredProcedureNew wadProcObj = new AccessStoredProcedureNew();
            wadProcObj.setTransactingId("URN", urn);
            wadProcObj.setProcedureName(ProcedureName.GETMERCHANTDATA.procedureName);
            wadProcObj.setTotalData(3);
            wadProcObj.setData(1, mid);
            wadProcObj.setData(2, tid);
            wadProcObj.setData(3, userid);

            wadProcObj.execute();

            wadProcObj.setResponse(dbResponseJson);

            wadProcObj.flush();

            String responseCode = dbResponseJson.optString(AccessProcedureEnum.PROC_RESPONSECODE.procEnum, "401");

            if (responseCode.equals(StatusCode.SUCCESS.getValue) && !(new JSONObject(new JSONArray(dbResponseJson.optString("dbJsonArrayResponseData")).opt(0).toString()).optString("RESPONSE_CODE").equals("401"))) {

                String responseData = dbResponseJson.optString("dbJsonArrayResponseData");

//                String response = "{\r\n    \"dbJsonArrayResponseData\": [\r\n        {\r\n            \"requestId\": \"null\",\r\n            \"tpaCred\": {\r\n                \"callUrl\": \"http: //10.20.30.28:5004/api/AEPS/ReqAuth\",\r\n                \"HMAC_KEY\": \"11111E1E57C9FEAF3879D04E6917874BABDE\",\r\n                \"AUTH_ID\": \"Test@123\",\r\n                \"AUTH_PWD\": \"Test@123\"\r\n            },\r\n            \"responseMessage\": \"Success\",\r\n            \"requestData\": {\r\n                \"PidData\": \"{PIDDATA}\",\r\n                \"isRKI\": \"Y\",\n            \"isREV\": \"Y\",\n            \"AadharNo\": \"{AADHARNO}\",\r\n                \"BankName\": \"607395\",\r\n                \"CustomerName\": \"{CUSTOMERNAME}\",\r\n                \"ReferenceNo\": \"{REFERENCENO}\",\r\n                \"Time\": \"{TIME}\",\r\n                \"Date\": \"{DATE}\",\r\n                \"AgentID\": \"9999999\",\r\n                \"BCName\": \"Rapipay\",\r\n                \"BCLoc\": \"NOIDA\",\r\n                \"TerminalID\": \"AGENT_TERMINAL_ID\",\r\n                \"VirtualID\": \"\",\r\n                \"STAN\": \"{STAN}\",\r\n                \"CardAcceptorName\": \"CARD_ACCEPTOR_NAME\",\r\n                \"DeviceID\": \"DEVICE_ID\"\r\n            },\r\n            \"responseCode\": \"200\"\r\n        }\r\n    ],\r\n    \"responseMessage\": \"Success\",\r\n    \"responseCode\": \"200\"\r\n}";

//                String responseData = new JSONObject(response).optString("dbJsonArrayResponseData");
                responseData = new JSONArray(responseData).optString(0);

                dbResponseJson = new JSONObject(responseData);
                responseModal.getApiResponseData().setResponseData(dbResponseJson);
            }else {

                responseModal.getApiResponseData().setResponseCode(StatusCode.SOMETHING_WENT_WRONG.getValue);
                responseModal.getApiResponseData().setResponseMessage(StatusCode.SOMETHING_WENT_WRONG.message);
            }
            log.info("Response got from stored Proc : {} ", dbResponseJson);

        } catch (NullPointerException e) {

            responseModal.getApiResponseData().setResponseCode(StatusCode.SOMETHING_WENT_WRONG.getValue);
            responseModal.getApiResponseData().setResponseMessage(StatusCode.SOMETHING_WENT_WRONG.message);
            log.error("error occurred while fetching mobi data to DB {} {} ",e.getMessage(),
                    e);
        } catch (Exception e) {

            responseModal.getApiResponseData().setResponseCode(StatusCode.SOMETHING_WENT_WRONG.getValue);
            responseModal.getApiResponseData().setResponseMessage(StatusCode.SOMETHING_WENT_WRONG.message);
            log.error("error occurred while fetching mobi data to DB {} {}",e.getMessage(), e);
        }
    }

}
