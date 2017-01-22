package org.mfaruga.MFSpringWebSockets;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class SampleEchoWebSocketConfigurer {

	@Bean
	WebSocketConfigurer webSocketConfigurer(final WebSocketHandler webSocketHandler) {
		return new WebSocketConfigurer() {

			public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
				registry.addHandler(new SampleTextWebSocketHandler(), "/path/wsAddress");
			}																									  
		};
	}

	@Bean
	WebSocketHandler myWebsocketHandler() {
		return new SampleTextWebSocketHandler();
	}

}
