package com.project.chatproject.domain.dto.chat;

import lombok.*;

import java.util.Map;

@Data
@Builder
public class ChatRoomDto {
    private String roomId; // 채팅방 아이디
    private String roomName; // 채팅방 이름
    private String roomPwd; // 채팅방 비밀번호
    private boolean secretChk; // 채팅방 잠금
    private int userCount; // 채팅방 인원수
    private int maxUserCnt; // 채팅방 최대 인원 제한
    private Long createDate;
    private Map<String, String> userlist;


    public ChatRoomDto plusUserCnt() {
        this.userCount += 1;
        return this;
    }

    public ChatRoomDto minusUserCnt() {
        if (userCount > 0) {
            this.userCount -= 1;
        }
        return this;
    }

    public ChatRoomDto changeRoom(ChatRoomDto chatRoomDto){
        this.roomName = chatRoomDto.getRoomName();
        this.roomPwd = chatRoomDto.getRoomPwd();
        this.userCount = chatRoomDto.getUserCount();
        this.secretChk = chatRoomDto.isSecretChk();
        return this;
    }
}
