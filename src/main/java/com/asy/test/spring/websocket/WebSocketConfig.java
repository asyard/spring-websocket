package com.asy.test.spring.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
@EnableWebSocketMessageBroker      // enables WebSocket message handling, backed by a message broker.
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

	@Override
	public void configureMessageBroker(MessageBrokerRegistry config) {
		// enable a simple memory-based message broker to carry the greeting messages
		// back to the client on destinations prefixed with "/topic"
		config.enableSimpleBroker("/topic");

		// designates the "/app" prefix for messages that are bound for @MessageMapping-annotated methods.
		config.setApplicationDestinationPrefixes("/app");
	}

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		// registers the "/hello" endpoint, enabling SockJS fallback options
		// so that alternative messaging options may be used if WebSocket is not available.
		// This endpoint, when prefixed with "/app", is the endpoint that the
		// GreetingController.greeting() method is mapped to handle.
		registry.addEndpoint("/hello").withSockJS();
	}

}