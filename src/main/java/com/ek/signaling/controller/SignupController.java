package com.ek.signaling.controller;

import com.ek.signaling.entity.Member;
import com.ek.signaling.repository.MemberRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignupController {
    private static final Logger logger = LoggerFactory.getLogger(SignupController.class);

    @Autowired
    MemberRepository memberRepository;

    @PostMapping("register")
    public String register(String userId, String pw, String userName) {
        memberRepository.save(new Member(userId, userName));
        return "success";
    }
}
