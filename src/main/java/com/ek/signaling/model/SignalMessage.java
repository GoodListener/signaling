package com.ek.signaling.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignalMessage {
    private MessageType type;
    private Object message;
    private String id;
    private String teamId;
    private String userName;
}
