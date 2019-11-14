package pl.coderslab.chatApp.Repos;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.chatApp.Model.Chatroom.ChatroomEntity;

import java.util.List;

public interface ChatroomRepository extends JpaRepository<ChatroomEntity, Long> {
    ChatroomEntity findByRoomName(String name);
    List<ChatroomEntity> findAll();
}
