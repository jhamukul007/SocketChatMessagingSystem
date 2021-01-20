package com.chat.messaging.configs;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

@Configuration
@EnableWebSocketMessageBroker
public class WebsoketChatUserConfig implements WebSocketMessageBrokerConfigurer {
	@Override
	public void configureMessageBroker(MessageBrokerRegistry config) {
		config.enableSimpleBroker("/topic/", "/queue/");
		config.setApplicationDestinationPrefixes("/app");
	}

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/greeting").setHandshakeHandler(new DefaultHandshakeHandler() {

			//Get sessionId from request and set it in Map attributes
			@SuppressWarnings({ "unchecked", "unused" })
			public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
					Map attributes) throws Exception {
				if (request instanceof ServletServerHttpRequest) {
					ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
					HttpSession session = servletRequest.getServletRequest().getSession();
					attributes.put("sessionId", session.getId());
				}
				return true;
			}}).withSockJS();
	}

}
