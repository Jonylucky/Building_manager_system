package com.building_mannager_system.configration;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebsocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic", "/queue"); // Cho phép gửi tin đến /topic, /queue
        config.setApplicationDestinationPrefixes("/app"); // Prefix cho các request gửi đến server
        config.setUserDestinationPrefix("/user"); // Cấu hình prefix cho các tin nhắn cá nhân
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws") // Endpoint WebSocket
                .setAllowedOrigins("http://localhost:3000") // Cho phép CORS (frontend ở port khác)
                .withSockJS(); // Kích hoạt SockJS để hỗ trợ các client không hỗ trợ WebSocket
    }



}