package pl.coderslab.chatApp.Model.User;

import org.springframework.stereotype.Service;

import pl.coderslab.chatApp.Model.Message.MessageEntity;
import pl.coderslab.chatApp.Repos.UserRepository;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    public UserService(UserMapper userMapper, UserRepository userRepository) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

    public UserEntity findByUserName(String name) {
        return userRepository.findByUsername(name);
    }
    public void save(UserEntity userEntity) {
        userRepository.save(userEntity);
    }
    public UserEntity findUserById(Long id) {
        return userRepository.getOne(id);
    }

    public void removeUserFromRoom(Long userId, Long roomId) {
        UserEntity userEntity = userRepository.getOne(userId);
        userEntity.getChatrooms().stream()
                .filter(room -> room.getId().equals(roomId))
                .forEach(room -> userEntity.getChatrooms().remove(room));
        userRepository.save(userEntity);
    }
}
