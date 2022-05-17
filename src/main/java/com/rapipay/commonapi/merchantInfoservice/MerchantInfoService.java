package com.rapipay.commonapi.merchantInfoservice;

import com.rapipay.commonapi.responsemodel.CommonApiResponseModal;

import javax.servlet.http.HttpServletRequest;

public interface MerchantInfoService {

    void merchantMain(String userId,String mid, String tid, String urn, CommonApiResponseModal CommonApiResponseModal);

    public void callProcedureToGetData(String userId,String mid, String tid, String urn, CommonApiResponseModal CommonApiResponseModal);

    public void setRedisData(String mid, String tid, String urn, CommonApiResponseModal CommonApiResponseModal);

    public void setDataFromDB(String mid, String tid, CommonApiResponseModal CommonApiResponseModal, String urn);

}
