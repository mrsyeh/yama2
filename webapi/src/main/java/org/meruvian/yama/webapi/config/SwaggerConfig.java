package org.meruvian.yama.webapi.config;

import org.jboss.resteasy.spi.ResteasyDeployment;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.wordnik.swagger.jaxrs.config.BeanConfig;	
import com.wordnik.swagger.jaxrs.listing.ApiDeclarationProvider;
import com.wordnik.swagger.jaxrs.listing.ApiListingResourceJSON;
import com.wordnik.swagger.jaxrs.listing.ResourceListingProvider;

@Configuration
public class SwaggerConfig {
	@Bean
	public BeanConfig beanConfig(ResteasyDeployment deployment) {
		BeanConfig config = new BeanConfig();
		config.setVersion("0.0.1-SNAPSHOT");
		config.setBasePath("/");
        config.setResourcePackage("org.meruvian.yama.webapi");
		config.setScan(true);		
		
		return config;
	}
	
	@Bean
	public ApiListingResourceJSON apiListingResourceJSON() {
		return new ApiListingResourceJSON();
	}
	
	@Bean
	public JacksonJsonProvider jacksonJsonProvider() {
		return new JacksonJsonProvider();
	}
	
	@Bean
	public ApiDeclarationProvider apiDeclarationProvider() {
		return new ApiDeclarationProvider();
	}
	
	@Bean
	public ResourceListingProvider esourceListingProvider() {
		return new ResourceListingProvider();
	}
	
	
}
