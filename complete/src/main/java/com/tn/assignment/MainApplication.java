package com.tn.assignment;

import java.util.Locale;
import java.util.Properties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

@SpringBootApplication(scanBasePackages="com.tn.assignment")
public class MainApplication {

	public static void main(String[] args) {
		
        SpringApplication app = new SpringApplication(MainApplication.class);
        final ConfigurableApplicationContext context = app.run(args);		

		// System.out.println("--------------- BEAN DEFINITION ----------------");
        // for (String beanName: context.getBeanDefinitionNames()) {
        //     System.out.println(beanName);
        // }	
		
		System.out.println("----------------- ENVIRONMENT ------------------");
		Environment env = context.getEnvironment();
		System.out.println("Started on port: " + env.getProperty("server.port"));		
	}

	@Bean
	public MessageSource messageSource1() {  

		//MessageSource messageSource = new ResourceBundleMessageSource();  
		ExposedResourceBundleMessageSource messageSource = new ExposedResourceBundleMessageSource();  
		messageSource.setBasename("lang/msg");  
		messageSource.setDefaultEncoding("UTF-8");
		//messageSource.setUseCodeAsDefaultMessage(true);
		
		// load message configs
		Properties englishMessages = messageSource.getProperties(Locale.ENGLISH);
		Properties thaiMessages = messageSource.getProperties(new Locale("th"));
		
		System.out.println("---------- EN ----------\n" + englishMessages);
		System.out.println("---------- TH ----------\n" + thaiMessages);

		return messageSource;  
	} 




}
