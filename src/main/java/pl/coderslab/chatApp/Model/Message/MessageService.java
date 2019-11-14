package pl.coderslab.chatApp.Model.Message;


import org.springframework.stereotype.Service;
import pl.coderslab.chatApp.Repos.MessageRepository;

import javax.transaction.Transactional;

@Service
@Transactional
public class MessageService {
    private final MessageMapper messageMapper;
    private final MessageRepository messageRepository;

    public MessageService(MessageMapper messageMapper, MessageRepository messageRepository) {
        this.messageMapper = messageMapper;
        this.messageRepository = messageRepository;
    }

    public Message mapToDto(MessageEntity messageEntity) {
        return messageMapper.convertToDto(messageEntity);
    }

    public void save(MessageEntity messageEntity) {
        messageRepository.save(messageEntity);
    }
}
