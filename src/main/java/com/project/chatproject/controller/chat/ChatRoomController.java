package com.project.chatproject.controller.chat;

import com.project.chatproject.Repository.ChatRepository;
import com.project.chatproject.domain.entity.ChatRoom;
import com.project.chatproject.service.user.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public String goChatRoom(Model model, @AuthenticationPrincipal PrincipalDetails principalDetails){
        model.addAttribute("list", chatRepository.findAllRoom());
        log.info("SHOW ALL ChatList {}", chatRepository.findAllRoom());
        log.info("roomName={}", model.getAttribute("roomName"));

        if(principalDetails != null){
            model.addAttribute("username", principalDetails.getUserRealName());
            log.info("user {}", principalDetails);
        }
        return "roomlist";
    }

    // 채팅방 생성
    @PostMapping("/chat/createroom")
    public String createRoom(
            @RequestParam("roomName") String name,
            @RequestParam("roomPwd")String pwd,
            @RequestParam(value = "secretChk")String secretChk,
            @RequestParam(value = "maxUserCnt", defaultValue = "100")String maxUserCnt,
            RedirectAttributes rttr){

        ChatRoom room = chatRepository.createChatRoom(name, pwd, Boolean.parseBoolean(secretChk), Integer.parseInt(maxUserCnt));
        log.info("CREATE Chat Room = {}", room);
        rttr.addFlashAttribute("roomName", room.getRoomName());
        return "redirect:/";
    }

    // 채팅방 입장 화면
    @GetMapping("/chat/room")
    public String roomDetail(Model model, String roomId, @AuthenticationPrincipal PrincipalDetails principalDetails){
        if(principalDetails != null){
            model.addAttribute("username", principalDetails.getUserRealName());
            log.info("user {}", principalDetails);
        }

        log.info("roomId = {}", roomId);
        model.addAttribute("room",chatRepository.findRoomById(roomId));
        return "chatroom";
    }

    // 채팅방 비밀번호 확인
    @PostMapping("/chat/confirmPwd/{roomId}")
    @ResponseBody
    public boolean confirmPwd(@PathVariable String roomId, @RequestParam String roomPwd){

        // 넘어온 roomId 와 roomPwd 를 이용해서 비밀번호 찾기
        // 찾아서 입력받은 roomPwd 와 room pwd 와 비교해서 맞으면 true, 아니면  false
        return chatRepository.confirmPwd(roomId, roomPwd);
    }

    // 채팅방 삭제
    @GetMapping("/chat/delRoom/{roomId}")
    public String delChatRoom(@PathVariable String roomId){

        // roomId 기준으로 chatRoomMap 에서 삭제, 해당 채팅룸 안에 있는 사진 삭제
        chatRepository.delChatRoom(roomId);

        return "redirect:/";
    }

    // 채팅방 인원 수 체크
    @GetMapping("/chat/chkUserCnt/{roomId}")
    @ResponseBody
    public boolean chUserCnt(@PathVariable String roomId){

        return chatRepository.chkRoomUserCnt(roomId);
    }
}
