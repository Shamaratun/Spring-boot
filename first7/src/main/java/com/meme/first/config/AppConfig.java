package com.meme.first.config;

import java.time.Duration;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Configuration
public class AppConfig{
	@Bean
	public DataSource dataSource () {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
dataSource.setUrl("jdbc:oracle:thin:@localhost:1521/ORCLPDB");
		dataSource.setUsername("orclpdbuser");
		dataSource.setPassword("isdb62");
		dataSource.setInitialSize(5);
		dataSource.setMaxTotal(20);
		dataSource.setMaxIdle(10);
		dataSource.setMinIdle(5);
		dataSource.setMaxWait(Duration.ofMillis(10000) );
		return dataSource; 
	}
	@Bean
	public  JdbcTemplate jdbcTemplate(DataSource dataSource ) {
		return new JdbcTemplate(dataSource);
		
		
	}
	@Bean
	public DataSourceTransactionManager transactionManager (DataSource dataSource ) {
		return new DataSourceTransactionManager(dataSource);
 }
}

