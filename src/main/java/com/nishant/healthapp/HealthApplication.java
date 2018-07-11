package com.nishant.healthapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.stereotype.Controller;

@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
@Controller
public class HealthApplication extends SpringBootServletInitializer {
	
	final static Logger logger = LoggerFactory.getLogger(HealthApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(HealthApplication.class, args);
	}

}
