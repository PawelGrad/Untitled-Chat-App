package pl.coderslab.chatApp.Repos;

        import org.springframework.data.jpa.repository.JpaRepository;
        import pl.coderslab.chatApp.Model.User.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
        UserEntity findByUsername(String username);
}
