package pl.coderslab.chatApp.Repos;

        import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.data.jpa.repository.Modifying;
        import org.springframework.data.jpa.repository.Query;
        import pl.coderslab.chatApp.Model.Chatroom.ChatroomEntity;
        import pl.coderslab.chatApp.Model.User.UserEntity;

        import javax.transaction.Transactional;
        import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
        UserEntity findByUsername(String username);
        void deleteById(Long id);

        @Modifying
        @Transactional
        @Query(value = "insert into authorities(username,authority) values (?,'ROLE_USER');", nativeQuery = true)
        void insertUserAuthority(String user);


        @Query(value = "SELECT u.*\n" +
                "FROM chatrooms AS c\n" +
                "INNER JOIN user_chatroom AS uc ON c.id = uc.chatroom_id\n" +
                "INNER JOIN users AS u ON uc.user_id = u.id\n" +
                "WHERE c.id = ?;", nativeQuery = true)
        List<UserEntity> findRoomsUsers(Long id);

}
