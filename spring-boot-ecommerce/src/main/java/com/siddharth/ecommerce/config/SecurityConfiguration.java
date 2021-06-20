package com.siddharth.ecommerce.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.okta.spring.boot.oauth.Okta;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		//protect endpoint /api/orders
		http.authorizeRequests()
			.antMatchers("/api/orders/**") //apply rule for this path and all subpaths
			.authenticated()
			.and()
			.oauth2ResourceServer() // Configures OAuth2 Resource Server Support
			.jwt(); // enables JWT encoded bearer token support
		
		//add CORS filter
		http.cors();
		
		//force a non-empty response body for 401 to make response more friendly
		Okta.configureResourceServer401ResponseBody(http);
		
		//disable CSRF since we are not using Cookies for Session Tracking
		http.csrf().disable();
	}
}
