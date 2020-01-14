package pl.project.chatApp.Repos;

import org.springframework.data.jpa.repository.JpaRepository;


import pl.project.chatApp.Model.Message.MessageEntity;

import java.util.List;


public interface MessageRepository extends JpaRepository<MessageEntity, Long> {

    List<MessageEntity> findByChatroom_Id(Long id);

}
