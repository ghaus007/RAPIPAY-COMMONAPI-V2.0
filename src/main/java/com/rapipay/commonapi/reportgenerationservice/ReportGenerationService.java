package com.rapipay.commonapi.reportgenerationservice;

import com.rapipay.commonapi.responsemodel.CommonApiResponseModal;

public interface ReportGenerationService {

    void reportMain(String urn,String auth,String mid, String tid, String fromDate, String toDate,String reqFrom,String reportType, CommonApiResponseModal commonApiResponseModal);

    public void getReportData(String urn,String reportType, String request, CommonApiResponseModal commonApiResponseModal);
}
