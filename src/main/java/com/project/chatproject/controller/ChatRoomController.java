package com.project.chatproject.controller;

import com.project.chatproject.Repository.ChatRepository;
import com.project.chatproject.domain.entity.ChatRoom;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatRoomController {

    private final ChatRepository chatRepository;

    // 채팅방 생성
    @PostMapping("/createroom")
    public String createRoom(@RequestParam String name, RedirectAttributes rttr){
        ChatRoom room = chatRepository.createChatRoom(name);
        log.info("CREATE Chat Room {}", room);
        rttr.addFlashAttribute("roomName", room.getRoomName());
        return "redirect:/chat/chatlist";
    }

    // 채팅 리스트 화면
    @GetMapping("/chatlist")
    public String rooms(Model model){
        model.addAttribute("list", chatRepository.findAllRoom());
        log.info("SHOW ALL ChatList {}", chatRepository.findAllRoom());
        log.info("roomName={}", model.getAttribute("roomName"));
        return "/roomlist";
    }

    // 채팅방 목록
    @GetMapping("/rooms")
    public List<ChatRoom> room(){
        return chatRepository.findAllRoom();
    }

    // 채팅방 입장 화면
    @GetMapping("room/enter/{roomId}")
    public String roomDetail(Model model, @PathVariable String roomId){
        model.addAttribute("roomId",roomId);
        return "/chat/chat";
    }

    // 채팅방 조회(roomId로)
    @GetMapping("room/{roomId}")
    public ChatRoom roomInfo(@PathVariable String roomId){
        return chatRepository.findRoomById(roomId);
    }
}
