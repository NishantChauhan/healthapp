package com.nishant.healthapp;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.nishant.healthapp.interceptors.LoginInterceptor;

@Configuration
@EnableTransactionManagement
//@EnableWebMvc
@PropertySource("classpath:application.properties")
public class ApplicationConfiguration implements WebMvcConfigurer{
	@Value("${spring.datasource.driverClassName}")
	String driverClassName;
	@Value("${spring.datasource.url}")
	String url;
	@Value("${spring.datasource.username}")
	String username;
	@Value("${spring.datasource.password}")
	String password;

//	@Bean
//	public WebMvcConfigurer corsConfigurer() {
//		return new WebMvcConfigurer() {
//			@Override
//			public void addCorsMappings(CorsRegistry registry) {
//				registry.addMapping("/**").allowedOrigins("http://localhost:4200");
//			}
//		};
//	}

	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/login");
	}

	@Bean(name = "dataSource")
	public DataSource getDataSource() {
		DataSource dataSource = DataSourceBuilder.create().username(username).password(password).url(url)
				.driverClassName(driverClassName).build();
		return dataSource;
	}

	@Bean(name = "sessionFactory")
	public SessionFactory getSessionFactory(DataSource dataSource) {
		LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
		sessionBuilder.scanPackages("com.nishant.healthapp.domain");
		return sessionBuilder.buildSessionFactory();
	}

	@Bean(name = "transactionManager")
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
		return transactionManager;
	}

	// Platform Transaction MAnager
	/*
	 * @Bean(name = "dataSource") public DataSource getDataSource() { DataSource
	 * dataSource =
	 * DataSourceBuilder.create().username(username).password(password).url(url)
	 * .driverClassName(driverClassName).build(); return dataSource; }
	 * 
	 * @Bean(name = "transactionManager") public DataSourceTransactionManager
	 * getTransactionManager(DataSource dataSource) { return new
	 * DataSourceTransactionManager(dataSource); }
	 * 
	 */

	// JTA Transaction Management
	/*
	 * @Bean(name = "dataSource") public DataSource dataSource() {
	 * JndiObjectFactoryBean dataSource = new JndiObjectFactoryBean();
	 * dataSource.setJndiName("jdbc:healthapp"); return dataSource; }
	 * 
	 * @Bean(name = "transactionManager") public JtaTransactionManager
	 * transactionManager() { return new JtaTransactionManager(); }
	 * 
	 * 
	 */

}
