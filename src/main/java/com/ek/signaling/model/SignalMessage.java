package com.ek.signaling.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignalMessage {
    private MessageType type;
    private String message;
    private String id;
}
