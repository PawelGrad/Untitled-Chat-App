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
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.coderslab.chatApp.Model.Chatroom.ChatroomEntity;

import pl.coderslab.chatApp.Model.Chatroom.ChatroomService;
import pl.coderslab.chatApp.Model.Message.MessageEntity;
import pl.coderslab.chatApp.Model.Message.MessageService;
import pl.coderslab.chatApp.Model.User.UserEntity;
import pl.coderslab.chatApp.Model.User.UserService;
import pl.coderslab.chatApp.Repos.ChatroomRepository;
import pl.coderslab.chatApp.Repos.UserRepository;


import static java.lang.String.format;

@Controller
@RequestMapping("/chat")
public class ChatRoomController {

    private static final Logger logger = LoggerFactory.getLogger(ChatRoomController.class);

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    private final MessageService messageService;
    private final UserService userService;
    private final ChatroomService chatroomService;


    public ChatRoomController(MessageService messageService, UserService userService, ChatroomService chatroomService) {
        this.messageService = messageService;
        this.userService = userService;
        this.chatroomService = chatroomService;
    }

    @MessageMapping("/chat/{roomId}/sendMessage")
    public void sendMessage(@DestinationVariable String roomId, @Payload MessageEntity chatMessage) {
        logger.info(roomId+" Chat message recieved is "+chatMessage.getContent());


        System.out.println(roomId);

        ChatroomEntity chatroom = chatroomService.findByRoomName(roomId);
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
        model.addAttribute("chatroom", new ChatroomEntity());
        return "addChatroom";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String processForm(@ModelAttribute ChatroomEntity chatroom, BindingResult result) {
        if(result.hasErrors()){
            return "redirect:add";
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        UserEntity user = userService.findByUserName(currentPrincipalName);
        chatroom.setChatOwner(user);
        chatroom.getUsers().add(user);
        user.getChatrooms().add(chatroom);
        userService.save(user);

        return "redirect:add";
    }
}
