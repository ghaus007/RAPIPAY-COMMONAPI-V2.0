package com.rapipay.commonapi.utils;

public enum StatusCode {
    SOMETHING_WENT_WRONG("401","something went wrong"),
    SUCCESS("200","success");

   public final String getValue;
   public final String message;
   private StatusCode(String getValue, String message)
   {
       this.getValue=getValue;
       this.message=message;
   }
}
