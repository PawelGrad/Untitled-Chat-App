package pl.coderslab.chatApp.Repos;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.chatApp.Model.Chatroom;

import java.util.List;

public interface ChatroomRepository extends JpaRepository<Chatroom, Long> {

    List<Chatroom> findAll();
}
