package com.project.chatproject.controller;

import com.project.chatproject.domain.dto.ChatRoomDto;
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

    private final ChatService chatService;

    @PostMapping
    public ChatRoom createChatRoom(@RequestParam String name){
        return chatService.createChatRoom(name);
    }

    @GetMapping
    public List<ChatRoom> findAllRoom(){
        return chatService.findAllRoom();
    }
}
