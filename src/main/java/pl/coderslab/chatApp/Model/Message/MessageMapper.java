package pl.coderslab.chatApp.Model.Message;


import org.springframework.stereotype.Component;

@Component
public class MessageMapper {

    public static Message convertToDto(MessageEntity entity){
        Message message = new Message();
        message.setContent(entity.getContent());
        message.setId(entity.getId());
        message.setSender(entity.getSender());
        message.setMessageType(entity.getType().toString());
        return message;
    }

}
