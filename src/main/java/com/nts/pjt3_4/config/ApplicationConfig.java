package com.nts.pjt3_4.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = { "com.nts.pjt3_4.dao",  "com.nts.pjt3_4.service", "com.nts.pjt3_4.test"})
@Import({ DBConfig.class })
public class ApplicationConfig {

}
