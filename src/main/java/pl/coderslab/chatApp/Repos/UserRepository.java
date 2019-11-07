package pl.coderslab.chatApp.Repos;

        import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.stereotype.Repository;
        import pl.coderslab.chatApp.Model.User;

        import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
        User findByUsername(String username);
}
