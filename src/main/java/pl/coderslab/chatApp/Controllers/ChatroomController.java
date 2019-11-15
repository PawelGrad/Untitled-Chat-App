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
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.chatApp.Model.Chatroom.ChatroomEntity;

import pl.coderslab.chatApp.Model.Chatroom.ChatroomService;
import pl.coderslab.chatApp.Model.Invitation.InvitationEntity;
import pl.coderslab.chatApp.Model.Invitation.InvitationService;
import pl.coderslab.chatApp.Model.Message.MessageEntity;
import pl.coderslab.chatApp.Model.Message.MessageService;
import pl.coderslab.chatApp.Model.User.UserEntity;
import pl.coderslab.chatApp.Model.User.UserService;


import java.util.List;

import static java.lang.String.format;

@Controller
@RequestMapping("/")
public class ChatroomController {

    private static final Logger logger = LoggerFactory.getLogger(ChatroomController.class);

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    private final MessageService messageService;
    private final UserService userService;
    private final ChatroomService chatroomService;
    private final InvitationService invitationService;

    public ChatroomController(MessageService messageService, UserService userService, ChatroomService chatroomService, InvitationService invitationService) {
        this.messageService = messageService;
        this.userService = userService;
        this.chatroomService = chatroomService;
        this.invitationService = invitationService;
    }


    @MessageMapping("/chat/{roomId}/sendMessage")
    public void sendMessage(@DestinationVariable String roomId, @Payload MessageEntity chatMessage) {
        logger.info(roomId+" Chat message recieved is "+chatMessage.getContent());

        ChatroomEntity chatroom = chatroomService.findByRoomName(roomId);
        chatMessage.setChatroom(chatroom);
        messageService.save(chatMessage);

        if(chatMessage.getType() == MessageEntity.MessageType.INVITE)
        {

            if(userService.findByUserName(chatMessage.getContent()) != null) {

                InvitationEntity invitationEntity = new InvitationEntity();
                invitationEntity.setRoom(chatroomService.findByRoomName(roomId));
                invitationEntity.setInvitee(userService.findByUserName(chatMessage.getContent()));
                invitationEntity.setInviter(userService.findByUserName(chatMessage.getSender()));
                invitationEntity.setAccepted(false);

                invitationService.save(invitationEntity);
            }
        } else {

            messagingTemplate.convertAndSend(format("/chat-room/%s", roomId), messageService.mapToDto(chatMessage));
        }
    }

    @MessageMapping("/chat/{roomId}/addUser")
    public void addUser(@DestinationVariable String roomId, @Payload MessageEntity chatMessage,
                        SimpMessageHeaderAccessor headerAccessor) {

        messagingTemplate.convertAndSend(format("/chat-room/%s", roomId), chatMessage);
    }



    @RequestMapping(value = "/app/chat/add", method = RequestMethod.GET)
    public String addChatroom(Model model) {
        model.addAttribute("chatroom", new ChatroomEntity());
        return "addChatroom";
    }

    @RequestMapping(value = "/app/chat/add", method = RequestMethod.POST)
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

    @RequestMapping(value = "/app/chat", method = RequestMethod.GET)
    public String chatGet(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();


        //List<ChatroomEntity> rooms = chatroomService.findAll();
        List<ChatroomEntity> rooms = chatroomService.findUserRooms(userService.findByUserName(currentPrincipalName).getId());
        model.addAttribute("myRooms", rooms);
        model.addAttribute("user", currentPrincipalName);
        model.addAttribute("room", "Public");
        return "chat";
    }

    @RequestMapping(value = "/app/chat", method = RequestMethod.POST)
    public String chatPost(@RequestParam("myRoom") String myRoom, Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        List<ChatroomEntity> rooms = chatroomService.findUserRooms(userService.findByUserName(currentPrincipalName).getId());
        model.addAttribute("myRooms", rooms);
        model.addAttribute("user", currentPrincipalName);
        model.addAttribute("room", myRoom);
        return "chat";
    }
}
