package pl.project.chatApp.Model.Chatroom;

import org.springframework.stereotype.Service;
import pl.project.chatApp.Exceptions.RoomAlreadyExistsException;
import pl.project.chatApp.Repos.ChatroomRepository;


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

    public void add(ChatroomEntity chatroomEntity) throws RoomAlreadyExistsException {
        if (chatroomRepository.findByRoomName(chatroomEntity.getRoomName()) == null) {
            chatroomRepository.save(chatroomEntity);
        } else {
            throw new RoomAlreadyExistsException("Room already exists");
        }
    }

    public void update(ChatroomEntity chatroomEntity) {
        chatroomRepository.save(chatroomEntity);
    }

    public ChatroomEntity findByRoomName(String roomname) {
        return chatroomRepository.findByRoomName(roomname);
    }

    public Long getChatOwnerId(Long id) {
        return chatroomRepository.getChatOwnerId(id);
    }

    public List<ChatroomEntity> findAll() {return chatroomRepository.findAll();};
    public List<ChatroomEntity> findUserMemberRooms(Long id) {return chatroomRepository.findUserMemeberRooms(id);};
    public List<ChatroomEntity> findUserRooms(Long id) {return chatroomRepository.findUserRooms(id);};
    public ChatroomEntity findRoomById(Long id) {
        return chatroomRepository.getOne(id);
    }


    public List<ChatroomEntity> findRoomsOwnedByUser(Long id) {
        return chatroomRepository.findRoomsOwnedByUser(id);
    }

    public void remove(Long id) {
        chatroomRepository.deleteById(id);
    }

}
