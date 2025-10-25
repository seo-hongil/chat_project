package com.project.chatproject.controller;
import com.project.chatproject.domain.dto.ChatDto;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import com.project.chatproject.domain.entity.ChatRoom;
import com.project.chatproject.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatController {

    private final SimpMessageSendingOperations template;

    @MessageMapping("/message")
    public void message(ChatDto chat){
        if(ChatDto.MessageType.ENTER.equals(chat.getType())){
            chat.setMessage(chat.getSender()+"님이 입장하셨습니다.");

            // sub/chat/room/roomId로 메세지 전송
            // convertAndSend(destination, payload) : 특정 주제 메세지를 전송
            // convertAndSendToUser(user, destination, payload) : 특정 사용자에게만 메세지를 전송
            // send(destination, Message<?> message) : 메세지 객체를 직접 전송
            template.convertAndSend("/sub/chat/room"+ chat.getRoomId(), chat);
            // 이제 구독하고 있는 구독자들이 날린 메세지를 보고 볼 수 있다.
        }
    }

}
