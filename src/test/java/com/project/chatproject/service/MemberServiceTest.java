package com.project.chatproject.service;

import com.project.chatproject.Repository.MemberRepository;
import com.project.chatproject.domain.entity.Member;
import com.project.chatproject.service.chat.ChatService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class MemberServiceTest {
    @Autowired
    MemberRepository memberRepository;

    @Autowired
    ChatService chatService;


    public Member createUser(){
        Member user = Member.builder()
                .name("testtest1")
                .pwd("test1")
                .email("test@test.com")
                .role("naver")
                .build();

        return user;
    }

    @Test
    @DisplayName("유저 테스트")
    public void userTest(){
        memberRepository.saveAndFlush(createUser());

        Member user = memberRepository.findByEmail("test@test.com")
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));

        System.out.println("user :" + user.getId() + "/" + user.getName() + "/" + user.getRole());
    }
}