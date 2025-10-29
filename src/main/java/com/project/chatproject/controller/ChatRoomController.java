package com.project.chatproject.controller;

import com.project.chatproject.Repository.ChatRepository;
import com.project.chatproject.domain.entity.ChatRoom;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ChatRoomController {

    private final ChatRepository chatRepository;

    // 채팅 리스트 화면
    @GetMapping("/")
    public String goChatRoom(Model model){
        model.addAttribute("list", chatRepository.findAllRoom());
        log.info("SHOW ALL ChatList {}", chatRepository.findAllRoom());
        log.info("roomName={}", model.getAttribute("roomName"));
        return "roomlist";
    }

    // 채팅방 생성
    @PostMapping("/chat/createroom")
    public String createRoom(@RequestParam String name, RedirectAttributes rttr){
        ChatRoom room = chatRepository.createChatRoom(name);
        log.info("CREATE Chat Room {}", room);
        rttr.addFlashAttribute("roomName", room.getRoomName());
        return "redirect:/";
    }

    // 채팅방 입장 화면
    @GetMapping("/chat/room")
    public String roomDetail(Model model, String roomId){
        log.info("roomId = {}", roomId);
        model.addAttribute("room",chatRepository.findRoomById(roomId));
        return "chatroom";
    }
}
