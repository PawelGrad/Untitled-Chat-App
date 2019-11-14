package pl.coderslab.chatApp.Model.Chatroom;

import org.springframework.stereotype.Service;
import pl.coderslab.chatApp.Model.Message.MessageEntity;
import pl.coderslab.chatApp.Repos.ChatroomRepository;


import javax.transaction.Transactional;

@Service
@Transactional
public class ChatroomService {

    private final ChatroomMapper chatroomMapper;
    private final ChatroomRepository chatroomRepository;

    public ChatroomService(ChatroomMapper chatroomMapper, ChatroomRepository chatroomRepository) {
        this.chatroomMapper = chatroomMapper;
        this.chatroomRepository = chatroomRepository;
    }

    public void save(ChatroomEntity chatroomEntity) {
        chatroomRepository.save(chatroomEntity);
    }

    public ChatroomEntity findByRoomName(String roomname) {
        return chatroomRepository.findByRoomName(roomname);
    }
}
