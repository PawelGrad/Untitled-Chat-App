package pl.coderslab.chatApp.Repos;

import org.springframework.data.jpa.repository.JpaRepository;


import pl.coderslab.chatApp.Model.Message.MessageEntity;


public interface MessageRepository extends JpaRepository<MessageEntity, Long> {

}
