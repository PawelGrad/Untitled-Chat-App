package pl.coderslab.chatApp.Controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.coderslab.chatApp.Model.Chatroom;
import pl.coderslab.chatApp.Model.Message;
import pl.coderslab.chatApp.Model.User;
import pl.coderslab.chatApp.Repos.ChatroomRepository;
import pl.coderslab.chatApp.Repos.MessageRepository;
import pl.coderslab.chatApp.Repos.UserRepository;


import static java.lang.String.format;

@Controller
@RequestMapping("/chat")
public class ChatRoomController {

    private static final Logger logger = LoggerFactory.getLogger(ChatRoomController.class);
    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    private final ChatroomRepository chatroomRepository;
    private final UserRepository userRepository;
    private final MessageRepository messageRepository;

    public ChatRoomController(ChatroomRepository chatroomRepository, UserRepository userRepository, MessageRepository messageRepository) {
        this.chatroomRepository = chatroomRepository;
        this.userRepository = userRepository;
        this.messageRepository = messageRepository;
    }

    @MessageMapping("/chat/{roomId}/sendMessage")
    public void sendMessage(@DestinationVariable String roomId, @Payload Message chatMessage) {
        logger.info(roomId+" Chat message recieved is "+chatMessage.getContent());
        messageRepository.save(chatMessage);
        messagingTemplate.convertAndSend(format("/chat-room/%s", roomId), chatMessage);
    }

    @MessageMapping("/chat/{roomId}/addUser")
    public void addUser(@DestinationVariable String roomId, @Payload Message chatMessage,
                        SimpMessageHeaderAccessor headerAccessor) {
        String currentRoomId = (String) headerAccessor.getSessionAttributes().put("room_id", roomId);
        if (currentRoomId != null) {
            Message leaveMessage = new Message();
            leaveMessage.setType(Message.MessageType.LEAVE);
            leaveMessage.setSender(chatMessage.getSender());
            messagingTemplate.convertAndSend(format("/chat-room/%s", currentRoomId), leaveMessage);
        }
        headerAccessor.getSessionAttributes().put("name", chatMessage.getSender());
        messagingTemplate.convertAndSend(format("/chat-room/%s", roomId), chatMessage);
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addChatroom(Model model) {
        model.addAttribute("chatroom", new Chatroom());
        return "addChatroom";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String processForm(@ModelAttribute Chatroom chatroom, BindingResult result) {
        if(result.hasErrors()){
            return "redirect:add";
        }
        chatroomRepository.save(chatroom);
        return "redirect:add";
    }
}
