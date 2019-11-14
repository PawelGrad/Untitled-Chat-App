package pl.coderslab.chatApp.Repos;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.chatApp.Model.Chatroom.Chatroom;

import java.util.List;

public interface ChatroomRepository extends JpaRepository<Chatroom, Long> {
    Chatroom findByRoomName(String name);
    List<Chatroom> findAll();
}
