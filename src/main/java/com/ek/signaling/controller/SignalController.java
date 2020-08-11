package com.ek.signaling.controller;

import com.ek.signaling.model.SignalMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class SignalController {

    private static final Logger logger = LoggerFactory.getLogger(SignalController.class);

    @MessageMapping("/signal.join")
    @SendTo("/topic/public")
    public SignalMessage join(@Payload SignalMessage signalMessage, SimpMessageHeaderAccessor headerAccessor) {
        logger.info("signal.join : " + signalMessage);
        headerAccessor.getSessionAttributes().put("userId", signalMessage.getId());

        return signalMessage;
    }

    @MessageMapping("/signal.leave")
    @SendTo("/topic/public")
    public SignalMessage leave(@Payload SignalMessage signalMessage, SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().remove("userId");
        return signalMessage;
    }
}
