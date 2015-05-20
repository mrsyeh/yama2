
package org.meruvian.yama.webapi.config;

import javax.sql.DataSource;

import liquibase.integration.spring.SpringLiquibase;

import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;


@Configuration
public class DatabaseConfig implements EnvironmentAware {
	private RelaxedPropertyResolver dbProp;
	
	@Bean(destroyMethod = "shutdown")
	public DataSource dataSource() {
		HikariConfig config = new HikariConfig();
		config.setDataSourceClassName(dbProp.getProperty("datasource"));
		config.setMaximumPoolSize(dbProp.getProperty("max_pool_size", int.class));
		config.setConnectionTestQuery(dbProp.getProperty("test_query"));
		
		config.addDataSourceProperty("url", dbProp.getProperty("url"));
		config.addDataSourceProperty("user", dbProp.getProperty("username"));
		config.addDataSourceProperty("password", dbProp.getProperty("password"));
		
		return new HikariDataSource(config);
	}

	@Bean
	public SpringLiquibase liquibase(DataSource dataSource) {
		SpringLiquibase liquibase = new SpringLiquibase();
		liquibase.setDataSource(dataSource);
		liquibase.setChangeLog("classpath:/db/yama.xml");
		liquibase.setShouldRun(dbProp.getProperty("run_migration", boolean.class));
		liquibase.setContexts("dev, prod");
		
		return liquibase;
	}
	
	@Override
	public void setEnvironment(Environment environment) {
		this.dbProp = new RelaxedPropertyResolver(environment, "db.");
	}
}
