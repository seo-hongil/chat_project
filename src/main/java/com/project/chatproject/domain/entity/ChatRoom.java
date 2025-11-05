package com.project.chatproject.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import java.util.UUID;

@Entity
@Getter
@Builder
@ToString
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatRoom {

    @Id
    @Builder.Default
    private final String roomId = UUID.randomUUID().toString(); // 채팅방 번호

    @NonNull
    private String roomName; // 채팅방 이름

    private String roomPwd; // 채팅방 삭제시 필요한 pwd

    private boolean secretChk; // 채팅방 잠금 여부

    @Builder.Default
    private int userCount = 0; // 채팅방 인원수

    private int maxUserCnt; // 채팅방 최대 인원 제한

    @Builder.Default
    private final Long createDate = System.currentTimeMillis(); // 생성일
}
