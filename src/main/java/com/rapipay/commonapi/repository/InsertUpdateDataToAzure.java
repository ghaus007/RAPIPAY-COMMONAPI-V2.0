package com.rapipay.commonapi.repository;


import javax.transaction.Transactional;

import com.rapipay.commonapi.requestmodel.CommonApiAzureRequestDto;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
@Scope("prototype")
public interface InsertUpdateDataToAzure extends JpaRepository<CommonApiAzureRequestDto, String> {
    @Transactional
    @Modifying
    @Query(
            value = "update [glbConfig].[CONFIG_SETTING] set SETTING_VALUE =:settingValue where SETTING_NAME =:settingName",
            nativeQuery = true
    )
    void updateData(String settingValue, String settingName);

    @Transactional
    @Query(
            value = " select SETTING_NAME from [glbConfig].[CONFIG_SETTING] where SETTING_NAME=:settingName ",
            nativeQuery = true
    )
    String checkParameter(String settingName);
}
