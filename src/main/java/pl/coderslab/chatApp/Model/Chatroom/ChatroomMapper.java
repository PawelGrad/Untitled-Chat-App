package pl.coderslab.chatApp.Model.Chatroom;

import org.springframework.stereotype.Component;
import pl.coderslab.chatApp.Model.User.User;
import pl.coderslab.chatApp.Model.User.UserEntity;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ChatroomMapper {

    public ChatroomEntity convertToEntity(Chatroom chatroom) {
        ChatroomEntity chatroomEntity = new ChatroomEntity();
        chatroomEntity.setId(chatroom.getId());
        chatroomEntity.setRoomName(chatroom.getRoomName());
        chatroomEntity.setChatOwner(chatroom.getChatOwner());
        chatroomEntity.setMessages(chatroom.getMessages());
        chatroomEntity.setUsers(chatroom.getUsers());
        return chatroomEntity;
    }

    public Chatroom convertToDto(ChatroomEntity chatroomEntity) {
        Chatroom chatroom = new Chatroom();
        chatroom.setId(chatroomEntity.getId());
        chatroom.setRoomName(chatroomEntity.getRoomName());
        chatroom.setChatOwner(chatroomEntity.getChatOwner());
        chatroom.setMessages(chatroomEntity.getMessages());
        chatroom.setUsers(chatroomEntity.getUsers());
        return chatroom;
    }

    public Set<ChatroomEntity> mapListToEntity(Set<Chatroom> chatrooms) {
        return chatrooms.stream().map(this::convertToEntity).collect(Collectors.toSet());
    }

    public Set<Chatroom> mapListToDto(Set<ChatroomEntity> chatroomEntities) {
        return chatroomEntities.stream().map(this::convertToDto).collect(Collectors.toSet());
    }


}
