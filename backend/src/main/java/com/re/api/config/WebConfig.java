package com.re.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {

	  @Bean
	    public WebMvcConfigurer corsConfigurer() {
	        return new WebMvcConfigurer() {
	            @Override
	            public void addCorsMappings(CorsRegistry registry) {
	                registry.addMapping("/**") // apply to all endpoints
	                        .allowedOrigins("http://localhost:4200") // Angular app
	                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // allow preflighted DELETE
	                        .allowedHeaders("*")
	                        .allowCredentials(true);
	                
	                
	            }
	            
	            // ✅ IMAGE FILE SERVING CONFIG
	            @Override
	            public void addResourceHandlers(ResourceHandlerRegistry registry) {
	                registry
	                    .addResourceHandler("/uploads/**")
	                    .addResourceLocations(
	                        "file:" + System.getProperty("user.dir") + "/uploads/"
	                    );
	            }
	        };
	    }
}
