package com.tn.assignment;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;

@SpringBootApplication(scanBasePackages="com.tn.assignment")
public class MainApplication {

	public static void main(String[] args) {
		
        SpringApplication app = new SpringApplication(MainApplication.class);
        final ConfigurableApplicationContext context = app.run(args);		

		// System.out.println("--------------- BEAN DEFINITION ----------------");
        // for (String beanName: context.getBeanDefinitionNames()) {
        //     System.out.println(beanName);
        // }	
		
		// System.out.println("----------------- ENVIRONMENT ------------------");
		// Environment env = context.getEnvironment();
		// System.out.println("Started on port: " + env.getProperty("server.port"));		
	}

	@Bean
	public MessageSource messageSource1() {  

		//MessageSource messageSource = new ResourceBundleMessageSource();  
		ExposedResourceBundleMessageSource messageSource = new ExposedResourceBundleMessageSource();  
		messageSource.setBasename("lang/msg");  
		messageSource.setDefaultEncoding("UTF-8");
		//messageSource.setUseCodeAsDefaultMessage(true);

		System.out.println("---------- EN ----------\n" + messageSource.getProperties(Locale.ENGLISH));
		System.out.println("---------- TH ----------\n" + messageSource.getProperties(new Locale("th")));

		return messageSource;  
	} 




}
