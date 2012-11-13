package net.cyberward.springjersey.config;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateExceptionTranslator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class DatabaseConfig {
	
	@Value("${jdbc.driverClassName}")
	private String driverClassName;
	
	@Value("${jdbc.url}")
	private String url;
	
	@Value("${jdbc.username}")
	private String userName;
	
	@Value("${jdbc.password}")
	private String password;
	
	@Value("${hibernate.dialect}")
	private String dialect;

	@Value("${hibernate.hbm2ddl.auto}")
	private String hbm2ddlAuto;
	
	@Value("${hibernate.show_sql}")
	private String showSql;

	@Bean 
	public PersistenceExceptionTranslationPostProcessor persistenceExceptionTranslationPostProcessor()
	{
		return new PersistenceExceptionTranslationPostProcessor();
	}
	
	@Bean
	public HibernateExceptionTranslator hibernateExceptionTranslator()
	{
		return new HibernateExceptionTranslator();
	}
	
	@Bean
	public DriverManagerDataSource dataSource()
	{
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(driverClassName);
		dataSource.setUrl(url);
		dataSource.setUsername(userName);
		dataSource.setPassword(password);
		return dataSource;
	}
	
	@Bean
	public EntityManagerFactory entityManagerFactory()
	{
		HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
		jpaVendorAdapter.setDatabasePlatform(dialect);

		Map<String, String> jpaProperties = new HashMap<String, String>();
		jpaProperties.put("hibernate.hbm2ddl.auto", hbm2ddlAuto);
		jpaProperties.put("hibernate.show_sql", showSql);
		
		LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactory.setDataSource(dataSource());
		entityManagerFactory.setJpaVendorAdapter(jpaVendorAdapter);
		entityManagerFactory.setJpaPropertyMap(jpaProperties);
		entityManagerFactory.setPackagesToScan("net.cyberward.springjersey");
		entityManagerFactory.setLoadTimeWeaver(new InstrumentationLoadTimeWeaver());
		entityManagerFactory.afterPropertiesSet();
		return entityManagerFactory.getNativeEntityManagerFactory();
	}
	
	@Bean
	public JpaTransactionManager transactionManager()
	{
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setDataSource(dataSource());
		transactionManager.setEntityManagerFactory(entityManagerFactory());
		return transactionManager;
	}
	
}
