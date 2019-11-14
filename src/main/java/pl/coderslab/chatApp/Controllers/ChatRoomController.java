package pl.coderslab.chatApp.Controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.coderslab.chatApp.Model.Chatroom.Chatroom;

import pl.coderslab.chatApp.Model.Message.MessageEntity;
import pl.coderslab.chatApp.Model.Message.MessageService;
import pl.coderslab.chatApp.Model.User.User;
import pl.coderslab.chatApp.Repos.ChatroomRepository;
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
    private final MessageService messageService;


    public ChatRoomController(ChatroomRepository chatroomRepository, UserRepository userRepository, MessageService messageService) {
        this.chatroomRepository = chatroomRepository;
        this.userRepository = userRepository;

        this.messageService = messageService;
    }

    @MessageMapping("/chat/{roomId}/sendMessage")
    public void sendMessage(@DestinationVariable String roomId, @Payload MessageEntity chatMessage) {
        logger.info(roomId+" Chat message recieved is "+chatMessage.getContent());


        System.out.println(roomId);
        Chatroom chatroom = chatroomRepository.findByRoomName(roomId);
        chatMessage.setChatroom(chatroom);
        messageService.save(chatMessage);

        messagingTemplate.convertAndSend(format("/chat-room/%s", roomId), messageService.mapToDto(chatMessage));

    }

    @MessageMapping("/chat/{roomId}/addUser")
    public void addUser(@DestinationVariable String roomId, @Payload MessageEntity chatMessage,
                        SimpMessageHeaderAccessor headerAccessor) {

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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User user = userRepository.findByUsername(currentPrincipalName);
        chatroom.setChatOwner(user);
        chatroom.getUsers().add(user);
        user.getChatrooms().add(chatroom);
        userRepository.save(user);

        return "redirect:add";
    }
}
