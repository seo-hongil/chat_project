package com.project.chatproject.domain.entity;

import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ChatRoom {
    private String roomId; // 채팅방 번호
    private String roomName; // 채팅방 이름
    private String creator; // 채팅방 생성자
    private Long createDate; // 생성일
    private int userCount; // 채팅방 인원수
    private int maxUserCnt; // 채팅방 최대 인원 제한
}
