package pl.coderslab.chatApp.Model.User;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public UserEntity convertToEntity(User user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setEnabled(user.isEnabled());
        userEntity.setId(user.getId());
        userEntity.setChatrooms(user.getChatrooms());
        userEntity.setMyChatroom(user.getMyChatroom());
        userEntity.setEmail(user.getEmail());
        userEntity.setUsername(user.getUsername());
        return userEntity;
    }

    public User convertToDto(UserEntity userEntity) {
        User user = new User();
        user.setEnabled(userEntity.isEnabled());
        user.setId(userEntity.getId());
        user.setChatrooms(userEntity.getChatrooms());
        user.setMyChatroom(userEntity.getMyChatroom());
        user.setEmail(userEntity.getEmail());
        user.setUsername(userEntity.getUsername());
        return user;
    }

    public List<UserEntity> mapListToEntity(List<User> users) {
        return users.stream().map(this::convertToEntity).collect(Collectors.toList());
    }

    public List<User> mapListToDto(List<UserEntity> userEntities) {
        return userEntities.stream().map(this::convertToDto).collect(Collectors.toList());
    }
}
