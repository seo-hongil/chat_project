package com.project.chatproject.service.chat;

import com.project.chatproject.domain.entity.ChatRoom;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatService {

    @Value("${chat.room.max_user_count}")
    private int MAX_USER_COUNT;
    private Map<String, ChatRoom> chatRooms;

    @PostConstruct
    private void init() {
        chatRooms = new LinkedHashMap<>();
    }

    // 채팅방 만들기
    public ChatRoom createChatRoom(String roomName) {
        // roomName 와 roomPwd 로 chatRoom 빌드 후 return
        String roomId = UUID.randomUUID().toString();
        ChatRoom room = ChatRoom.builder()
                .roomId(roomId)
                .roomName(roomName)
                .build();

        chatRooms.put(roomId, room);

        return room;
    }

    // 채티방 검증
    public void validateRoom(String roomName, int maxUserCnt) throws BadRequestException{
        if(maxUserCnt > MAX_USER_COUNT){
            throw new BadRequestException("최대인원을 초과할 수 없습니다.");
        }

    }
    // 전체 채팅방 조회
    public List<ChatRoom> findAllRoom(){
        return new ArrayList(chatRooms.values());
    }
    // roomId로 방 조회
    public ChatRoom findRoomById(String roomId) {
        return chatRooms.get(roomId);
    }
    // 채팅방 삭제

    // 채팅방 수정

}
