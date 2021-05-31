package br.com.softbank.consultawebservice.config;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import liquibase.integration.spring.SpringLiquibase;

@Configuration
@Primary
public class DBConfig {

	@Bean
	@ConfigurationProperties(prefix="spring.datasource")
    public DataSource dataSource() {
		return DataSourceBuilder.create().build();
    }
	
	@Bean
	public SpringLiquibase liquibase() {
	    SpringLiquibase liquibase = new SpringLiquibase();
	    liquibase.setChangeLog("classpath:db/changelog/liquibase-changeLog.xml");
	    liquibase.setDataSource(dataSource());
	    return liquibase;
	}
}
