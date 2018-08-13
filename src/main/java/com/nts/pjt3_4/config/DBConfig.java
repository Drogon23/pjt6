package com.nts.pjt3_4.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:database.properties")
@MapperScan("com.nts.pjt3_4.dao")
public class DBConfig implements TransactionManagementConfigurer {

	@Value("${driverClassName}")
	private String driverClassName;
	@Value("${dbUrl}")
	private String url;
	@Value("${dbUser}")
	private String username;
	@Value("${dbPasswd}")
	private String password;

	@Bean
	public DataSource dataSource() {
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
	
	@Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
      SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
      sqlSessionFactory.setDataSource(dataSource());
      org.apache.ibatis.session.Configuration config = new org.apache.ibatis.session.Configuration();
      config.setMapUnderscoreToCamelCase(true);
      sqlSessionFactory.setMapperLocations(
		  new PathMatchingResourcePatternResolver().getResources("mapper/*.xml"));
      sqlSessionFactory.setTypeAliasesPackage("com.nts.pjt3_4.dto");
      return (SqlSessionFactory) sqlSessionFactory.getObject();
    }
	
	@Bean
    public SqlSessionTemplate sqlSession(SqlSessionFactory sqlSessionFactory) {
    	return new SqlSessionTemplate(sqlSessionFactory);
    }


}
