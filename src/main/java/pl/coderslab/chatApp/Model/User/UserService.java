package pl.coderslab.chatApp.Model.User;

import org.springframework.stereotype.Service;

import pl.coderslab.chatApp.Model.Chatroom.ChatroomEntity;
import pl.coderslab.chatApp.Model.Message.MessageEntity;
import pl.coderslab.chatApp.Repos.UserRepository;

import javax.transaction.Transactional;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.Collectors;

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

        Set<ChatroomEntity> newSet =  userEntity.getChatrooms().stream()
                .filter(room -> !room.getId().equals(roomId))
                .collect(Collectors.toSet());
        userEntity.setChatrooms(newSet);
        userRepository.save(userEntity);
    }
}
