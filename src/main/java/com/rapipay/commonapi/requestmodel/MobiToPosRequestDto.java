package com.rapipay.commonapi.requestmodel;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class MobiToPosRequestDto {


    private String panCardDocformate;
    private String kycCity;
    private String kycDocNamePan;
    private String gstnNo;
    private String bankName;
    private String mcc;
    private String accountHolderName;
    private String merchantName;
    private String isGstn;
    private String kycStateName;
    private String businessCity;
    private String nameOnPan;
    private String isBusiness;
    private String businessStateName;
    private String docAddress;
    private String merchantType;
    private String email;
    private String kycBusinessName;
    private String panCardDocPath;
    private String mobileNo;
    private String panNumber;
    private String accountNumber;
    private String ifscCode;
    private String kycDocNo;
    private String midCico;
    private String urn;
    private String dobOnDoc;
    private String deviceSerialNo;
    private String kycPinCode;
    private String refId;
    private String isBankAutoVerified;

    private String kycDistrict;
    private String kycAddress;
    private String businessDistrict;
    private String businessAddress;
    private String businessPinCode;
    private String businessName;
    private String kycDocumentType;

    public String getPanCardDocformate() {
        return panCardDocformate;
    }

    public void setPanCardDocformate(String panCardDocformate) {
        this.panCardDocformate = panCardDocformate;
    }

    public String getKycCity() {
        return kycCity;
    }

    public void setKycCity(String kycCity) {
        this.kycCity = kycCity;
    }

    public String getKycDocNamePan() {
        return kycDocNamePan;
    }

    public void setKycDocNamePan(String kycDocNamePan) {
        this.kycDocNamePan = kycDocNamePan;
    }

    public String getGstnNo() {
        return gstnNo;
    }

    public void setGstnNo(String gstnNo) {
        this.gstnNo = gstnNo;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getMcc() {
        return mcc;
    }

    public void setMcc(String mcc) {
        this.mcc = mcc;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getIsGstn() {
        return isGstn;
    }

    public void setIsGstn(String isGstn) {
        this.isGstn = isGstn;
    }

    public String getKycStateName() {
        return kycStateName;
    }

    public void setKycStateName(String kycStateName) {
        this.kycStateName = kycStateName;
    }

    public String getBusinessCity() {
        return businessCity;
    }

    public void setBusinessCity(String businessCity) {
        this.businessCity = businessCity;
    }

    public String getNameOnPan() {
        return nameOnPan;
    }

    public void setNameOnPan(String nameOnPan) {
        this.nameOnPan = nameOnPan;
    }

    public String getIsBusiness() {
        return isBusiness;
    }

    public void setIsBusiness(String isBusiness) {
        this.isBusiness = isBusiness;
    }

    public String getBusinessStateName() {
        return businessStateName;
    }

    public void setBusinessStateName(String businessStateName) {
        this.businessStateName = businessStateName;
    }

    public String getDocAddress() {
        return docAddress;
    }

    public void setDocAddress(String docAddress) {
        this.docAddress = docAddress;
    }

    public String getMerchantType() {
        return merchantType;
    }

    public void setMerchantType(String merchantType) {
        this.merchantType = merchantType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getKycBusinessName() {
        return kycBusinessName;
    }

    public void setKycBusinessName(String kycBusinessName) {
        this.kycBusinessName = kycBusinessName;
    }

    public String getPanCardDocPath() {
        return panCardDocPath;
    }

    public void setPanCardDocPath(String panCardDocPath) {
        this.panCardDocPath = panCardDocPath;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getPanNumber() {
        return panNumber;
    }

    public void setPanNumber(String panNumber) {
        this.panNumber = panNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getIfscCode() {
        return ifscCode;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    public String getKycDocNo() {
        return kycDocNo;
    }

    public void setKycDocNo(String kycDocNo) {
        this.kycDocNo = kycDocNo;
    }

    public String getMidCico() {
        return midCico;
    }

    public void setMidCico(String midCico) {
        this.midCico = midCico;
    }

    public String getUrn() {
        return urn;
    }

    public void setUrn(String urn) {
        this.urn = urn;
    }

    public String getDobOnDoc() {
        return dobOnDoc;
    }

    public void setDobOnDoc(String dobOnDoc) {
        this.dobOnDoc = dobOnDoc;
    }

    public String getDeviceSerialNo() {
        return deviceSerialNo;
    }

    public void setDeviceSerialNo(String deviceSerialNo) {
        this.deviceSerialNo = deviceSerialNo;
    }

    public String getKycPinCode() {
        return kycPinCode;
    }

    public void setKycPinCode(String kycPinCode) {
        this.kycPinCode = kycPinCode;
    }

    public String getRefId() {
        return refId;
    }

    public void setRefId(String refId) {
        this.refId = refId;
    }

    public String getIsBankAutoVerified() {
        return isBankAutoVerified;
    }

    public void setIsBankAutoVerified(String isBankAutoVerified) {
        this.isBankAutoVerified = isBankAutoVerified;
    }

    public String getKycDistrict() {
        return kycDistrict;
    }

    public void setKycDistrict(String kycDistrict) {
        this.kycDistrict = kycDistrict;
    }

    public String getKycAddress() {
        return kycAddress;
    }

    public void setKycAddress(String kycAddress) {
        this.kycAddress = kycAddress;
    }

    public String getBusinessDistrict() {
        return businessDistrict;
    }

    public void setBusinessDistrict(String businessDistrict) {
        this.businessDistrict = businessDistrict;
    }

    public String getBusinessAddress() {
        return businessAddress;
    }

    public void setBusinessAddress(String businessAddress) {
        this.businessAddress = businessAddress;
    }

    public String getBusinessPinCode() {
        return businessPinCode;
    }

    public void setBusinessPinCode(String businessPinCode) {
        this.businessPinCode = businessPinCode;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getKycDocumentType() {
        return kycDocumentType;
    }

    public void setKycDocumentType(String kycDocumentType) {
        this.kycDocumentType = kycDocumentType;
    }

    @Override
    public String toString() {
        return "MobiToPosRequestDto{" +
                "panCardDocformate='" + panCardDocformate + '\'' +
                ", kycCity='" + kycCity + '\'' +
                ", kycDocNamePan='" + kycDocNamePan + '\'' +
                ", gstnNo='" + gstnNo + '\'' +
                ", bankName='" + bankName + '\'' +
                ", mcc='" + mcc + '\'' +
                ", accountHolderName='" + accountHolderName + '\'' +
                ", merchantName='" + merchantName + '\'' +
                ", isGstn='" + isGstn + '\'' +
                ", kycStateName='" + kycStateName + '\'' +
                ", businessCity='" + businessCity + '\'' +
                ", nameOnPan='" + nameOnPan + '\'' +
                ", isBusiness='" + isBusiness + '\'' +
                ", businessStateName='" + businessStateName + '\'' +
                ", docAddress='" + docAddress + '\'' +
                ", merchantType='" + merchantType + '\'' +
                ", email='" + email + '\'' +
                ", kycBusinessName='" + kycBusinessName + '\'' +
                ", panCardDocPath='" + panCardDocPath + '\'' +
                ", mobileNo='" + mobileNo + '\'' +
                ", panNumber='" + panNumber + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", ifscCode='" + ifscCode + '\'' +
                ", kycDocNo='" + kycDocNo + '\'' +
                ", midCico='" + midCico + '\'' +
                ", urn='" + urn + '\'' +
                ", dobOnDoc='" + dobOnDoc + '\'' +
                ", deviceSerialNo='" + deviceSerialNo + '\'' +
                ", kycPinCode='" + kycPinCode + '\'' +
                ", refId='" + refId + '\'' +
                ", isBankAutoVerified='" + isBankAutoVerified + '\'' +
                ", kycDistrict='" + kycDistrict + '\'' +
                ", kycAddress='" + kycAddress + '\'' +
                ", businessDistrict='" + businessDistrict + '\'' +
                ", businessAddress='" + businessAddress + '\'' +
                ", businessPinCode='" + businessPinCode + '\'' +
                ", businessName='" + businessName + '\'' +
                ", kycDocumentType='" + kycDocumentType + '\'' +
                '}';
    }

}
