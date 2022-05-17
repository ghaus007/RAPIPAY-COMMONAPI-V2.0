package com.rapipay.commonapi.userinfoservice;

import com.rapipay.commonapi.responsemodel.CommonApiResponseModal;

public interface UserInfoService {

    void userMain(String userId, String urn,String auth, CommonApiResponseModal commonApiResponseModal);

    public void validateRequest(String urn, String auth, CommonApiResponseModal commonApiResponseModal);

    public void callProcedureToGetData(String userId, String urn, CommonApiResponseModal commonApiResponseModal);

    public void setRedisData(String userId, String urn, CommonApiResponseModal commonApiResponseModal);

    public void setDataFromDB(String userId, CommonApiResponseModal commonApiResponseModal, String urn);

}
