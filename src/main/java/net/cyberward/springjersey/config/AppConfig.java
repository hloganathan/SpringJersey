package net.cyberward.springjersey.config;


import javax.validation.Validation;
import javax.validation.Validator;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ClassPathResource;

@Configuration
@ComponentScan(basePackages = "net.cyberward.springjersey")
@Import({DatabaseConfig.class})
public class AppConfig {

	@Bean
    public PropertyPlaceholderConfigurer getPropertyPlaceholderConfigurer()
    {
		System.out.println("BEAN INJECTION...");
        PropertyPlaceholderConfigurer ppc = new PropertyPlaceholderConfigurer();
        ppc.setLocation(new ClassPathResource("application.properties"));
        ppc.setIgnoreUnresolvablePlaceholders(true);
        return ppc;
    }
	
	@Bean(name = "validator")
	public Validator validator() {
		return Validation.buildDefaultValidatorFactory().getValidator();
	}
}
