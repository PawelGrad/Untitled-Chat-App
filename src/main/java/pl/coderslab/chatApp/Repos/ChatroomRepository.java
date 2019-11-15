package pl.coderslab.chatApp.Repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.chatApp.Model.Chatroom.ChatroomEntity;

import java.util.List;

public interface ChatroomRepository extends JpaRepository<ChatroomEntity, Long> {
    ChatroomEntity findByRoomName(String name);
    List<ChatroomEntity> findAll();

    @Query(value = "SELECT c.*\n" +
            "FROM users AS u\n" +
            "INNER JOIN user_chatroom AS uc ON u.id = uc.user_id\n" +
            "INNER JOIN chatrooms AS c ON uc.chatroom_id = c.id\n" +
            "WHERE u.id = ?;", nativeQuery = true)
    List<ChatroomEntity> findUserRooms(Long id);

   // void addUserToChatroom();
}
