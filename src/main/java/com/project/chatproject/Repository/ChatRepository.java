package com.project.chatproject.Repository;

import com.project.chatproject.domain.entity.ChatRoom;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.*;

// pub/sub 사용 시 구독자 관리가 알아서 된다.
// 따라서 메세지 발송, 세션관리 코드 구현 불필요!
@Repository
public class ChatRepository {

    private Map<String, ChatRoom> chatRoomMap = new LinkedHashMap<>();

    public ChatRoom createChatRoom(String roomName){
        ChatRoom chatRoom = new ChatRoom().create(roomName);
        chatRoomMap.put(chatRoom.getRoomId(), chatRoom);

        return chatRoom;
    }

    public List<ChatRoom> findAllRoom(){
        // 채팅방 생성 순서를 최근순으로 반환
        List chatRooms = new ArrayList(chatRoomMap.values());
        Collections.reverse(chatRooms);
        return chatRooms;
    }

    public ChatRoom findRoomById(String roomId){
        return chatRoomMap.get(roomId);
    }
}
