package com.project.chatproject.service;

import com.project.chatproject.domain.dto.chat.ChatRoomDto;
import com.project.chatproject.domain.dto.user.MemberDto;
import com.project.chatproject.domain.entity.Member;
import com.project.chatproject.mapper.MemberMapper;
import com.project.chatproject.service.chat.ChatService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class ChatServiceTest {

    @Autowired
    ChatService repository;

    ChatRoomDto room;

    void createRoom(){
        room = repository.createChatRoom("newTEST", "newPwd", true, 120);
    }

    Member createUser(){
        Member user = Member.builder()
                .id(1L)
                .email("asdf@naver.com")
                .pwd("testPWD")
                .name("testNick")
                .role("naver")
                .build();
        return user;
    }

    @Test
    void addUser() {
        createRoom();
        String uuid = repository.addUser(room.getRoomId(), "test");
        System.out.println("uuid : " + uuid);
    }

    @Test
    void delUser() {
    }

    @Test
    void getUserName() {
    }

    @Test
    void getUserList() {
    }

    @Test
    void isDuplicateName() {
        addUser();
        String user = repository.isDuplicateName(room.getRoomId(), "test");
        System.out.println("user : " + user);
    }

    @Test
    @DisplayName("Mapping TEST")
    void entityToDtoMapping(){
        Member user = createUser();
        System.out.println("Entity 출력 : " + user.getClass());

        MemberDto dto = MemberMapper.INSTANCE.toDto(user);
        System.out.println("DTO 출력 : " + dto.getClass());
        System.out.println("내용 출력 : " + dto.getId() + " / " + dto.getName());
    }
}
