package com.nts.pjt3.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

@Configuration
@EnableTransactionManagement
public class DBConfig implements TransactionManagementConfigurer {

	private String driverClassName;
	private Properties properties;
	private String url;
	private String username;
	private String password;

	@Bean
	public DataSource dataSource() {
		InputStream inputStream = DBConfig.class.getResourceAsStream("/database.properties");
		properties = new Properties();
		try {
			properties.load(inputStream);
			url = properties.getProperty("dbUrl");
			username = properties.getProperty("dbUser");
			password = properties.getProperty("dbPasswd");
			driverClassName = properties.getProperty("driverClassName");
			Class.forName("com.mysql.cj.jdbc.Driver");
			inputStream.close();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(driverClassName);
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		return dataSource;
	}

	@Override
	public PlatformTransactionManager annotationDrivenTransactionManager() {
		return transactionManger();
	}

	@Bean
	public PlatformTransactionManager transactionManger() {
		return new DataSourceTransactionManager(dataSource());
	}
}
