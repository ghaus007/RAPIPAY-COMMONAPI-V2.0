package com.rapipay.commonapi.commonapiservice;


import com.rapipay.commonapi.requestmodel.CommonApiAzureRequestDto;
import com.rapipay.commonapi.requestmodel.CommonApiMongoDBRequestDto;
import com.rapipay.commonapi.responsemodel.CommonApiResponseModal;
import org.springframework.stereotype.Service;

@Service
public interface CommonApiService {
    void main(String auth, String urn, CommonApiMongoDBRequestDto commonApiMongoDBRequestDto, CommonApiAzureRequestDto commonApiAzureRequestDto, CommonApiResponseModal commonApiResponseModal);

    void callMethodToInsertDataInMongo(String urn, CommonApiMongoDBRequestDto commonApiRequestDto, CommonApiResponseModal commonApiResponseModal);

    void checkIfExists(String urn, CommonApiAzureRequestDto commonApiAzureRequestDto, CommonApiResponseModal commonApiResponseModal);

    void getMain(String auth, String urn, CommonApiResponseModal commonApiResponseModal);

    void callMethodToGetDataFromAzure(String urn, CommonApiResponseModal commonApiResponseModal);

    void callMethodToUpdateData(String urn, CommonApiAzureRequestDto commonApiAzureRequestDto, CommonApiResponseModal commonApiResponseModal);

}

