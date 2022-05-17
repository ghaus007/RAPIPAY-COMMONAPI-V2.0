package com.rapipay.commonapi.configuration;

import com.rapipay.config.ConfigWAD;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;


@Configuration
public class ConnectorWADConfig {
    public static final Logger log = LogManager.getLogger(ConnectorWADConfig.class);

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        try {
           // ConfigWAD.setSetSqlDataBaseName("POS");
            ConfigWAD.makeDatabaseConnection(dataSource);
        } catch (NullPointerException e) {
            log.error(e.getMessage() + "null pointer error at" + e);
        } catch (Exception e) {
            log.error(e.getMessage() + "Exception while connecting to DB");
        }
        return dataSource;
    }


}