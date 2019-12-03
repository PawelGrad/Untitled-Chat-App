package pl.coderslab.chatApp.Repos;

import org.hibernate.annotations.SQLDelete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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

    @Query(value = "SELECT c.*\n" +
            "FROM users AS u\n" +
            "INNER JOIN user_chatroom AS uc ON u.id = uc.user_id\n" +
            "INNER JOIN chatrooms AS c ON uc.chatroom_id = c.id\n" +
            "WHERE u.id = ? and u.id != c.user_id;", nativeQuery = true)
    List<ChatroomEntity> findUserMemeberRooms(Long id);

    @Query(value = "SELECT *" +
            "FROM chatrooms " +
            "WHERE user_id = ?;", nativeQuery = true)
    List<ChatroomEntity> findRoomsOwnedByUser(Long id);

    @Modifying
    @Query(value = "DELETE " +
            "FROM chatrooms " +
            "WHERE id = ?;", nativeQuery = true)
    void deleteById(Long id);

    @Query(value = "SELECT user_id FROM chatrooms WHERE id = ?1", nativeQuery = true)
    Long getChatOwnerId(Long id);
   // void addUserToChatroom();
}
