package com.project.chatproject.Repository;

import com.project.chatproject.domain.entity.ChatRoom;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.*;

// pub/sub 사용 시 구독자 관리가 알아서 된다.
// 따라서 메세지 발송, 세션관리 코드 구현 불필요!
@Repository
@Slf4j
public class ChatRepository {

    private Map<String, ChatRoom> chatRoomMap = new LinkedHashMap<>();

    // 채팅방 생성
    public ChatRoom createChatRoom(String roomName, String roomPwd, boolean secretChk, int maxUserCnt){
        ChatRoom chatRoom = ChatRoom.builder()
                .roomId(UUID.randomUUID().toString())
                .roomName(roomName)
                .roomPwd(roomPwd)
                .secretChk(secretChk)
                .userCount(0)
                .maxUserCnt(maxUserCnt)
                .userlist(new HashMap<String, String>())
                .build();

        chatRoomMap.put(chatRoom.getRoomId(), chatRoom);

        return chatRoom;
    }

    // 전체 채팅방 조회
    public List<ChatRoom> findAllRoom(){
        // 채팅방 생성 순서를 최근순으로 반환
        List chatRooms = new ArrayList(chatRoomMap.values());
        Collections.reverse(chatRooms);
        return chatRooms;
    }

    // roomId로 채팅방 조회
    public ChatRoom findRoomById(String roomId){
        return chatRoomMap.get(roomId);
    }

    // 채팅방 인원+1
    public void plusUserCnt(String roomId){
        ChatRoom room = chatRoomMap.get(roomId);
        room.plusUserCnt();
    }

    // 채팅방 인원-1
    public void minusUserCnt(String roomId){
        ChatRoom room = chatRoomMap.get(roomId);
        room.minusUserCnt();
    }

    // 채팅방 유저 리스트에 유저 추가
    public String addUser(String roomId, String userName){
        ChatRoom room = chatRoomMap.get(roomId);
        String userUUID = UUID.randomUUID().toString();
        room.getUserlist().put(userUUID, userName);

        return userUUID;
    }

    // 채팅방 유저 리스트 삭제
    public void delUser(String roomId, String userUUID){
        ChatRoom room = chatRoomMap.get(roomId);
        room.getUserlist().remove(userUUID);
    }

    // 채팅방 userName 조회
    public String getUserName(String roomId, String userUUID){
        ChatRoom room = chatRoomMap.get(roomId);
        return room.getUserlist().get(userUUID);
    }

    // 채팅방 전체 userlist 조회
    public ArrayList<String> getUserList(String roomId){
        ArrayList<String> list = new ArrayList<>();

        ChatRoom room = chatRoomMap.get(roomId);

        // hashmap 을 for 문을 돌린 후
        // value 값만 뽑아내서 list에 저장 후 reutrn
        room.getUserlist().forEach((key, value) -> list.add(value));
        return list;
    }

    // 채팅방 유저 이름 중복 확인
    public String isDuplicateName(String roomId, String username){
        ChatRoom room = chatRoomMap.get(roomId);
        String tmp = username;

        // 만약 userName 이 중복이라면 랜덤한 숫자를 붙임
        while(room.getUserlist().containsValue(tmp)){
            int ranNum = (int) (Math.random()*100)+1;

            tmp = username+ranNum;
        }

        return tmp;
    }

    // 채팅방 비밀번호 조회
    public boolean confirmPwd(String roomId, String roomPwd) {
        return roomPwd.equals(chatRoomMap.get(roomId).getRoomPwd());
    }

    // 채팅방 삭제
    public void delChatRoom(String roomId) {
        try {
            // 채팅방 삭제
            chatRoomMap.remove(roomId);
            log.info("삭제 완료 roomId : {}", roomId);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // maxUserCnt 에 따른 채팅방 입장 여부
    public boolean chkRoomUserCnt(String roomId){
        ChatRoom room = chatRoomMap.get(roomId);

        log.info("참여인원 확인 [{}, {}]", room.getUserCount(), room.getMaxUserCnt());

        if (room.getUserCount() + 1 > room.getMaxUserCnt()) {
            return false;
        }

        return true;
    }
}
