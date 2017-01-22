package org.mfaruga.MFSpringWebSockets;

import org.json.JSONObject;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class SampleTextWebSocketHandler extends TextWebSocketHandler {

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
				
		// there is no subprotocol defines - because of that sender and receiver needs to understand the format
		// in this case we're using JSON with 'clientMessage' field
		String payload = message.getPayload();
		JSONObject jsonObject = new JSONObject(payload);
		StringBuilder builder = new StringBuilder();
		builder.append("FROM MYSERVER-").append("Your Message: ").append(jsonObject.get("clientMessage"));
		session.sendMessage(new TextMessage(builder.toString()));
	}

}
