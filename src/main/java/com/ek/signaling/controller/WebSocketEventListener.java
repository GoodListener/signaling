package com.ek.signaling.controller;

import com.ek.signaling.model.MessageType;
import com.ek.signaling.model.SignalMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
public class WebSocketEventListener {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketEventListener.class);

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    @EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent event) {
        logger.info("session connected : " + event.toString());
    }

    @EventListener
    public void handleWebSocketDisconnectedListener(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());

        String userId = (String) headerAccessor.getSessionAttributes().get("userId");
        if(userId != null) {
            logger.info("User Disconnected : " + userId);

            SignalMessage signalMessage = new SignalMessage();
            signalMessage.setType(MessageType.LEAVE);
            signalMessage.setId(userId);
            signalMessage.setMessage("Leave user");

            messagingTemplate.convertAndSend("/topic/public", signalMessage);
        }
    }

}
