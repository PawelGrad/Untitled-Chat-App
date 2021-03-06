package pl.project.chatApp.Model.User;

import org.springframework.stereotype.Service;
import pl.project.chatApp.Exceptions.UserAlreadyExistsException;
import pl.project.chatApp.Model.Chatroom.ChatroomEntity;
import pl.project.chatApp.Repos.UserRepository;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService {


    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity findByUserName(String name) {
        return userRepository.findByUsername(name);
    }
    public void addUser(UserEntity userEntity) throws UserAlreadyExistsException {

            if(userRepository.findByUsername(userEntity.getUsername()) == null) {
                userEntity.setEnabled(true);
            userRepository.save(userEntity);
            userRepository.insertUserAuthority(userEntity.getUsername());


            } else {
                throw new UserAlreadyExistsException("User already exists");
            }
    }

    public List<UserEntity> findRoomsUsers(Long id) {
        return userRepository.findRoomsUsers(id);
    }

    public void updateUser(UserEntity userEntity){
            userRepository.save(userEntity);
    }
    public UserEntity findUserById(Long id) {
        return userRepository.getOne(id);
    }

    public void removeUserFromRoom(Long userId, Long roomId) {
        UserEntity userEntity = userRepository.getOne(userId);

        Set<ChatroomEntity> newSet =  userEntity.getChatrooms().stream()
                .filter(room -> !room.getId().equals(roomId))
                .collect(Collectors.toSet());
        userEntity.setChatrooms(newSet);
        userRepository.save(userEntity);
    }

    public void insertAuthority(String user) { userRepository.insertUserAuthority(user);}
}
