package com.springcore.orm.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.springcore.orm.dao.impl.StudentDaoImpl;

@Configuration
@EnableTransactionManagement
public class HibernateConfig {
	
	@Bean
	public Properties getHibernateProperties() {
		Properties properties = new Properties();
		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		properties.setProperty("hibernate.show_sql", "true");
		properties.setProperty("hibernate.format_sql", "true");
		properties.setProperty("hibernate.hbm2ddl.auto", "create");
		
		return properties;
	}
	
	@Bean
	public DriverManagerDataSource getDataSource() {
		DriverManagerDataSource datasource = new DriverManagerDataSource();
		datasource.setUrl("jdbc:mysql://localhost:3306/springorm");
		datasource.setUsername("root");
		datasource.setPassword("root1234");
		
		return datasource;
	}
	
	@Bean
	public LocalSessionFactoryBean getSessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(getDataSource());
		sessionFactory.setHibernateProperties(getHibernateProperties());
		sessionFactory.setPackagesToScan("com.springcore.orm.entities");
		
		return sessionFactory;
	}
	
	@Bean
	public HibernateTransactionManager getTransactionManager() {
		HibernateTransactionManager trxnManager = new HibernateTransactionManager();
		trxnManager.setSessionFactory(getSessionFactory().getObject());
		
		return trxnManager;
	}
	
	@Bean(name = "studentDao")
	public StudentDaoImpl getStudentDao() {
		StudentDaoImpl studentDao = new StudentDaoImpl();
		return studentDao;
	}
	
}
