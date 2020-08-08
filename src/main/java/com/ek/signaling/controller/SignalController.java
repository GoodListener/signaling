package com.ek.signaling.controller;

import com.ek.signaling.model.SignalMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class SignalController {

    @MessageMapping("/signal.join")
    public SignalMessage join(@Payload SignalMessage signalMessage, SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("username", signalMessage.getSender());
        return signalMessage;
    }

    @MessageMapping("/signal.leave")
    public SignalMessage leave(@Payload SignalMessage signalMessage, SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().remove("username");
        return signalMessage;
    }
}
