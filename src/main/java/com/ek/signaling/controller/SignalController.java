package com.ek.signaling.controller;

import com.ek.signaling.entity.Member;
import com.ek.signaling.entity.Team;
import com.ek.signaling.model.MessageType;
import com.ek.signaling.model.SignalMessage;
import com.ek.signaling.repository.MemberRepository;
import com.ek.signaling.repository.TeamRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;

@Controller
public class SignalController {

    private static final Logger logger = LoggerFactory.getLogger(SignalController.class);
    @Autowired
    private SimpMessageSendingOperations messagingTemplate;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    TeamRepository teamRepository;

    @MessageMapping("/signal.join.{teamId}")
    @SendTo("/topic/{teamId}")
    public SignalMessage join(@Payload SignalMessage signalMessage, SimpMessageHeaderAccessor headerAccessor) {
        logger.info("signal.join : " + signalMessage);
        sendInitialData(signalMessage.getId(), signalMessage.getTeamId());
        Map<String, Object> sessionAttributes = headerAccessor.getSessionAttributes();

        Member member = new Member(signalMessage.getId(), signalMessage.getUserName());
        Team team = new Team(signalMessage.getTeamId());
        member.setTeam(team);
        Object message = signalMessage.getMessage();
        teamRepository.save(team);
        memberRepository.save(member);
        sessionAttributes.put("userId", signalMessage.getId());
        sessionAttributes.put("teamId", signalMessage.getTeamId());

        return signalMessage;
    }

    private void sendInitialData(String userId, String teamId) {
        List<Member> memberList = memberRepository.findByTeamId(teamId);
        SignalMessage signalMessage = new SignalMessage();
        signalMessage.setId(userId);
        signalMessage.setTeamId(teamId);
        signalMessage.setMessage(memberList);
        signalMessage.setType(MessageType.USERLIST);
        messagingTemplate.convertAndSend("/topic/" + userId, signalMessage);
    }
}
