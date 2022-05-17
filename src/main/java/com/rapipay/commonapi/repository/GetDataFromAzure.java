package com.rapipay.commonapi.repository;

import com.rapipay.commonapi.requestmodel.CommonApiAzureRequestDto;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Repository
@Scope("prototype")
public interface GetDataFromAzure extends JpaRepository<CommonApiAzureRequestDto, Integer> {
    @Transactional
    @Modifying
    @Query(
            value = "select SETTING_NAME, SETTING_VALUE,SETTING_TYPE from [glbConfig].[CONFIG_SETTING]",
            nativeQuery = true
    )
    ArrayList<CommonApiAzureRequestDto> getData();
}
