package com.store;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

//@Configuration
//@EnableAutoConfiguration
//@ComponentScan
@SpringBootApplication // same as @Configuration @EnableAutoConfiguration @ComponentScan
public class StoreApplication {

	public static void main(String[] args) {
		//System.setProperty("spring.devtools.restart.enabled", "true");
		//System.setProperty("spring.main.banner-mode","off");
		SpringApplication.run(StoreApplication.class, args);
		//SpringApplication app = new SpringApplication(StoreApplication.class);
		//app.setBannerMode(Banner.Mode.OFF);
		//app.run(args);

	}
}
