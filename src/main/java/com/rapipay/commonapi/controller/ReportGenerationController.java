package com.rapipay.commonapi.controller;

import com.rapipay.commonapi.reportgenerationservice.ReportGenerationService;
import com.rapipay.commonapi.responsemodel.ApiResponseData;
import com.rapipay.commonapi.responsemodel.CommonApiResponseModal;
import com.rapipay.commonapi.utils.CommonApiEnum;
import com.rapipay.commonapi.utils.Constants;
import com.rapipay.commonapi.utils.StatusCode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class ReportGenerationController {
    public static final Logger log = LogManager.getLogger(CommonApiController.class);

    ReportGenerationService reportGenerationService;

    @GetMapping(value = "/getReportData/{reportType}/{fromDate}/{toDate}")
    public CommonApiResponseModal getReportGeneration(@RequestHeader String urn, @RequestHeader(value = "Authorization") String auth,@RequestHeader String reqFrom,@RequestHeader String mid, @RequestHeader String tid, @PathVariable String fromDate,@PathVariable String toDate, @PathVariable String reportType) {
        CommonApiResponseModal commonApiResponseModal = new CommonApiResponseModal();
        commonApiResponseModal.setApiResponseData(new ApiResponseData());

        reportGenerationService = getReportGenerationServiceObject();
        try {
            log.info("[URN_{}] Calling method to navigate to service layer ", urn);

//            this.commonApiService().reportMain(urn, auth, request, commonApiResponseModal);
            reportGenerationService.reportMain(urn,auth,mid,tid,fromDate,toDate,reqFrom,reportType,commonApiResponseModal);
            commonApiResponseModal.setApiResponseCode(CommonApiEnum.SUCCESS_RESPONSE_CODE.value);
            commonApiResponseModal.setApiResponseMessage(CommonApiEnum.SUCCESS_RESPONSE_MESSAGE.value);
        } catch (NullPointerException e) {
            log.error("Error in Getting report from database >>>>{} , {} ", e.getMessage(), e);
            commonApiResponseModal.setApiResponseCode(StatusCode.SOMETHING_WENT_WRONG.getValue);
            commonApiResponseModal.setApiResponseMessage(StatusCode.SOMETHING_WENT_WRONG.message);
        } catch (Exception e) {
            log.error("Error in Getting report from database >>>>{}", e.getMessage());
            commonApiResponseModal.setApiResponseCode(StatusCode.SOMETHING_WENT_WRONG.getValue);
            commonApiResponseModal.setApiResponseMessage(StatusCode.SOMETHING_WENT_WRONG.message);
        }

        commonApiResponseModal.setApiResponseDateTime(new SimpleDateFormat(Constants.RESPONSEDATEFORMAT).format(new Date()));
        commonApiResponseModal.setApiResponseFrom("COMMONREPORT");
        log.info("[URN_{}]Response sent to COMMON API calling : {}", commonApiResponseModal.toString());
        return commonApiResponseModal;

    }

    @Lookup
    public ReportGenerationService getReportGenerationServiceObject() {
        return null;
    }

}
