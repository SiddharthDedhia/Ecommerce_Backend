package com.siddharth.ecommerce.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyAppConfig implements WebMvcConfigurer{
	
	@Value("${allowed.origins}")
	private String[] theAllowedOrigins;
	
	@Value("${spring.data.rest.base-path}")
	private String basePath;
	
	@Override
	public void addCorsMappings(CorsRegistry cors) {
		//configure cors mapping
		cors.addMapping(basePath+"/**").allowedOrigins(theAllowedOrigins);
	}
}

//rest controller  configuration is seperate from spring data rest configuration. Now we can remoce crossorgin from restcontroller as well