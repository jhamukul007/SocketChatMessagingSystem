package com.chatmessage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.messaging.controller.chatcontroller.BuddyController;
import com.messaging.controller.chatcontroller.ChatController;
import com.messaging.controller.chatcontroller.ChatUserController;

@SpringBootApplication
@EnableScheduling
@ComponentScan(basePackageClasses = {BuddyController.class,ChatController.class,ChatUserController.class} )
public class ChatMessagingSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChatMessagingSystemApplication.class, args);
	}
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("http://localhost:8080");
			}
		};
	}
}
