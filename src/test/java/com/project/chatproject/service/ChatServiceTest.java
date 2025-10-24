package com.project.chatproject.service;

import com.project.chatproject.domain.entity.ChatRoom;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class ChatServiceTest {

    @Autowired
    private ChatService service;

    @Test
    void createRoom(){
        ChatRoom chatRoom = service.createChatRoom("TEST");
        System.out.println(chatRoom.toString());
    }
}
