package com.rapipay.commonapi;

import com.rapipay.config.ConfigWAD;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class CommonapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommonapiApplication.class, args);
	}

	@Autowired
	JdbcTemplate jdbcTemplate;


	public static final Logger log = LogManager.getLogger(CommonapiApplication.class);

	@PostConstruct
	private void init() {
		try {
			ConfigWAD.initStoredProcedureNew(jdbcTemplate);
			ConfigWAD.initReadRedisData();

			log.info("***********  INIT METHOD ************");
		} catch (Exception e) {
			log.error("Can not initialise init method {} {}", e.getMessage(), e);
		}
	}


}
