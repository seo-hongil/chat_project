package com.project.chatproject.domain.entity;

import lombok.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@Getter
@Builder
@ToString
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ChatRoom {

    @Builder.Default
    private final String roomId = UUID.randomUUID().toString(); // 채팅방 번호

    @NonNull
    private final String roomName; // 채팅방 이름

    private String roomPwd; // 채팅방 삭제시 필요한 pwd

    private boolean secretChk; // 채팅방 잠금 여부

    @Builder.Default
    private int userCount = 0; // 채팅방 인원수

    private int maxUserCnt; // 채팅방 최대 인원 제한

    @Builder.Default
    private final Long createDate = System.currentTimeMillis(); // 생성일

    @Builder.Default
    private final Map<String, String> userlist = new HashMap<>(); // 사용자 목록

    public ChatRoom plusUserCnt() {
        this.userCount += 1;
        return this;
    }

    public ChatRoom minusUserCnt() {
        if (userCount > 0) {
            this.userCount -= 1;
        }
        return this;
    }
}
