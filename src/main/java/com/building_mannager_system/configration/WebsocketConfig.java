package com.building_mannager_system.configration;

import com.building_mannager_system.component.WebSocketAuthInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketTransportRegistration;

@Configuration
@EnableWebSocketMessageBroker
public class WebsocketConfig implements WebSocketMessageBrokerConfigurer {


    private final WebSocketAuthInterceptor webSocketAuthInterceptor;

    // ✅ Constructor chính xác
    public WebsocketConfig(WebSocketAuthInterceptor webSocketAuthInterceptor) {
        this.webSocketAuthInterceptor = webSocketAuthInterceptor;
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic");  // Broker cho tin nhắn public
        config.setApplicationDestinationPrefixes("/app");  // Prefix cho request gửi đến server
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws")
                .setAllowedOriginPatterns("*")
                .addInterceptors(webSocketAuthInterceptor)
                .withSockJS();
        registry.addEndpoint("/ws")
                .addInterceptors(webSocketAuthInterceptor)  // ✅ Thêm Interceptor cho bảo mật
                .setAllowedOriginPatterns("*");
    }


}
