package com.rapipay.commonapi.utils;

public enum ProcedureName {
    GETMERCHANTDATA("[POS].[GET_MERCHANT_DETAIL_INFO]"),
    GETUSERDATA("[POS].[GET_MERCHANT_DETAIL_BY_USERID]"),
    GETREPORTDATA("[REPORT].[REPORT_");

    public final String procedureName;

    private ProcedureName(String procedureName) {
        this.procedureName = procedureName;
    }

}
