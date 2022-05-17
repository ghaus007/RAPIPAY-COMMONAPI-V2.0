package com.rapipay.commonapi.repository;

import com.rapipay.commonapi.responsemodel.CommonApiResponseModal;
import com.rapipay.commonapi.utils.ProcedureName;
import com.rapipay.commonapi.utils.StatusCode;
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
public class ReportDataResponse {

    public static final Logger log = LogManager.getLogger(MerchantInfoResponse.class);

    public void getReportData(String urn,String reportType, String request, CommonApiResponseModal commonApiResponseModal){
        try{
            JSONObject dbResponseJson = new JSONObject();
            JSONObject jsonObj = new JSONObject(request);
            AccessStoredProcedureNew wadProcObj=new AccessStoredProcedureNew();
            wadProcObj.setTransactingId("URN",urn);
            wadProcObj.setProcedureName(ProcedureName.GETREPORTDATA.procedureName+reportType+"]");
            String rexml = XML.toString(jsonObj,"requestData") ;
            wadProcObj.setTotalData(1);
            wadProcObj.setData(1,rexml);
            wadProcObj.execute();
            wadProcObj.setResponse(dbResponseJson);
            wadProcObj.flush();

            JSONArray temp = new JSONArray();
            if(dbResponseJson.optString("responseCode","401").equals("200")) {
                temp = new JSONArray(dbResponseJson.optString( "dbJsonArrayResponseData"));
//                dbResponseJson =new JSONObject(temp.optString(0));//to be checked
            }

            commonApiResponseModal.getApiResponseData().setResponseCode(dbResponseJson.optString("responseCode","401"));//replace with db enum
            commonApiResponseModal.getApiResponseData().setResponseMessage(dbResponseJson.optString("responseMessage"));//replace with db enum
            commonApiResponseModal.getApiResponseData().setResponseData(temp.toString());

            log.info("[URN_{}] Response received from procedure : {}",urn, temp);
        }catch (NullPointerException e) {

            commonApiResponseModal.getApiResponseData().setResponseCode(StatusCode.SOMETHING_WENT_WRONG.getValue);
            commonApiResponseModal.getApiResponseData().setResponseMessage(StatusCode.SOMETHING_WENT_WRONG.message);
            log.error("error occurred while fetching report data to DB {} {} ",e.getMessage(),
                    e);
        } catch (Exception e) {

            commonApiResponseModal.getApiResponseData().setResponseCode(StatusCode.SOMETHING_WENT_WRONG.getValue);
            commonApiResponseModal.getApiResponseData().setResponseMessage(StatusCode.SOMETHING_WENT_WRONG.message);
            log.error("error occurred while fetching report data to DB {} {}",e.getMessage(), e);
        }
    }
}
