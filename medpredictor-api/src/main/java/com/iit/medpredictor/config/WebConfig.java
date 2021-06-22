package com.iit.medpredictor.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuration class for web API application
 */
@Configuration
public class WebConfig implements WebMvcConfigurer
{
	@Override
	public void addCorsMappings( CorsRegistry registry )
	{
		registry.addMapping( "/**" ).allowedOrigins( "*" ).allowedMethods( "GET", "POST","PUT","DELETE", "PATCH" );
	}
}
