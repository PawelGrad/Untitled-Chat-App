package pl.project.chatApp.Model.Message;


import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MessageMapper {

    public Message convertToDto(MessageEntity entity){
        Message message = new Message();
        message.setContent(entity.getContent());
        message.setId(entity.getId());
        message.setSender(entity.getSender());
        message.setMessageType(entity.getType().toString());
        return message;
    }


    public List<Message> mapListToDto(List<MessageEntity> messegeEntities) {
        return messegeEntities.stream().map(this::convertToDto).collect(Collectors.toList());
    }
}
