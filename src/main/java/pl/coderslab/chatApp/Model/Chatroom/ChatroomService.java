package pl.coderslab.chatApp.Model.Chatroom;

import org.springframework.stereotype.Service;
import pl.coderslab.chatApp.Model.Message.MessageEntity;
import pl.coderslab.chatApp.Model.User.UserEntity;
import pl.coderslab.chatApp.Repos.ChatroomRepository;


import javax.transaction.Transactional;
import java.util.List;

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
    public List<ChatroomEntity> findAll() {return chatroomRepository.findAll();};
    public List<ChatroomEntity> findUserRooms(Long id) {return chatroomRepository.findUserRooms(id);};
  //  public void addUserToChatroom() { chatroomRepository.addUserToChatroom();};
}
