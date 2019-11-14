package pl.coderslab.chatApp.Repos;

        import org.springframework.data.jpa.repository.JpaRepository;
        import pl.coderslab.chatApp.Model.User.User;

public interface UserRepository extends JpaRepository<User, Long> {
        User findByUsername(String username);
}
