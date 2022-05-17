package com.rapipay.commonapi.utils;
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//


import com.rapipay.conf.appprop.ReadProperties;
import com.rapipay.commonapi.responsemodel.CommonApiResponseModal;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigInteger;
import java.util.Base64;

@Component
public class ValidateAuth {
    public static final Logger log = LogManager.getLogger(ValidateAuth.class);

    public ValidateAuth() {
    }

    public void validateAuth(String urn, String auth, CommonApiResponseModal commonApiResponseModal) {
        try {
            log.info("[URN_{}] Calling method to verify base auth ", urn);
            String authToken = "Basic " + Base64.getEncoder().encodeToString((ReadProperties.getPropertyData("AUTH_CREDENTIAL") + ":" + ReadProperties.getPropertyData("AUTH_PASSWORD")).getBytes());
            if (!authToken.equals(auth)) {
                commonApiResponseModal.getApiResponseData().setResponseCode(CommonApiEnum.FAIL_RESPONSE_CODE.value);
                commonApiResponseModal.getApiResponseData().setResponseMessage(CommonApiEnum.FAILED_AUTHORIZATION.value);
                commonApiResponseModal.getApiResponseData().setResponseData("{}");
            }
            else{
                commonApiResponseModal.getApiResponseData().setResponseCode("");
                commonApiResponseModal.getApiResponseData().setResponseMessage("");
                commonApiResponseModal.getApiResponseData().setResponseData("{}");
            }
        } catch (NullPointerException var5) {
            log.error("[URN_{}] Exception at verifying basic auth ", urn, var5.getMessage(), var5);
            commonApiResponseModal.getApiResponseData().setResponseCode(CommonApiEnum.FAIL_RESPONSE_CODE.value);
            commonApiResponseModal.getApiResponseData().setResponseMessage(CommonApiEnum.FAILED_AUTHORIZATION.value);
            commonApiResponseModal.getApiResponseData().setResponseData("{}");
        } catch (Exception var6) {
            log.error("[URN_{}] Exception at verifying auth ", urn, var6.getMessage());
            commonApiResponseModal.getApiResponseData().setResponseCode(CommonApiEnum.FAIL_RESPONSE_CODE.value);
            commonApiResponseModal.getApiResponseData().setResponseMessage(CommonApiEnum.FAILED_AUTHORIZATION.value);
            commonApiResponseModal.getApiResponseData().setResponseData("{}");
        }

    }

    private String hmacSHA512Convertor(String urn, String key, String authToken) {
        try {
            Mac hmacSHA512 = Mac.getInstance("HmacSHA512");
            SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), "HmacSHA512");
            hmacSHA512.init(secretKeySpec);
            byte[] digest = hmacSHA512.doFinal(authToken.getBytes());
            BigInteger hash = new BigInteger(1, digest);
            String result = hash.toString(16);
            if (result.length() % 2 != 0) {
                result = "0" + result;
            }

            return Base64.getEncoder().encodeToString(result.getBytes());
        } catch (NullPointerException var9) {
            log.error("[URN_{}] Exception in converting to HMACSHA512 : {} {} =======>: ", urn, var9.getMessage(), var9);
            return "";
        } catch (Exception e) {
            log.error("[URN_{}] Null Pointer Exception in converting to HMACSHA512 : {} =======>: ", urn, e.getMessage());
            return "";
        }
    }
}
