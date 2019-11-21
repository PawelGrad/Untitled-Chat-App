package pl.coderslab.chatApp.Controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.chatApp.Model.Chatroom.ChatroomEntity;
import pl.coderslab.chatApp.Model.Chatroom.ChatroomService;
import pl.coderslab.chatApp.Model.Invitation.InvitationEntity;
import pl.coderslab.chatApp.Model.Invitation.InvitationService;
import pl.coderslab.chatApp.Model.Message.MessageEntity;
import pl.coderslab.chatApp.Model.Message.MessageService;
import pl.coderslab.chatApp.Model.User.UserEntity;
import pl.coderslab.chatApp.Model.User.UserService;
import pl.coderslab.chatApp.Repos.ChatroomRepository;


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
    private final ChatroomRepository chatroomRepository;

    public ChatroomController(MessageService messageService, UserService userService, ChatroomService chatroomService, InvitationService invitationService, ChatroomRepository chatroomRepository) {
        this.messageService = messageService;
        this.userService = userService;
        this.chatroomService = chatroomService;
        this.invitationService = invitationService;
        this.chatroomRepository = chatroomRepository;
    }

    @MessageMapping("/chat/{roomId}/sendMessage")
    public void sendMessage(@DestinationVariable String roomId, @Payload MessageEntity chatMessage) {
        logger.info(roomId+" Chat message recieved is "+chatMessage.getContent());

        ChatroomEntity chatroom = chatroomService.findByRoomName(roomId);
        chatMessage.setChatroom(chatroom);
        if(chatMessage.getType() == MessageEntity.MessageType.INVITE)
        {

            if(userService.findByUserName(chatMessage.getContent()) != null && !roomId.equals("Public")) {
                InvitationEntity invitationEntity = new InvitationEntity();
                invitationEntity.setRoom(chatroomService.findByRoomName(roomId));
                invitationEntity.setInvitee(userService.findByUserName(chatMessage.getContent()));
                invitationEntity.setInviter(userService.findByUserName(chatMessage.getSender()));
                invitationService.save(invitationEntity);
            }
        } else if (chatMessage.getType() == MessageEntity.MessageType.BAN) {
            if(userService.findByUserName(chatMessage.getContent()) != null && !roomId.equals("Public")) {
                Long userId = userService.findByUserName(chatMessage.getContent()).getId();
                Long chatroomId = chatroomService.findByRoomName(roomId).getId();
                userService.removeUserFromRoom(userId, chatroomId);
                messagingTemplate.convertAndSend(format("/chat-room/%s", roomId), messageService.mapToDto(chatMessage));
            }
        } else {
            messageService.save(chatMessage);
            messagingTemplate.convertAndSend(format("/chat-room/%s", roomId), messageService.mapToDto(chatMessage));
        }
    }

    @MessageMapping("/chat/{roomId}/addUser")
    public void addUser(@DestinationVariable String roomId, @Payload MessageEntity chatMessage,
                        SimpMessageHeaderAccessor headerAccessor) {
        messagingTemplate.convertAndSend(format("/chat-room/%s", roomId), chatMessage);
        List<MessageEntity> messages = messageService.findChatroomMessages(chatroomService.findByRoomName(roomId).getId());
        messages.forEach(msg -> msg.setType(MessageEntity.MessageType.CHAT));
        messages.forEach(msg -> messagingTemplate.convertAndSendToUser(chatMessage.getSender(),"/queue/" + roomId, messageService.mapToDto(msg)));
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
        try {
            chatroomService.add(chatroom);
        } catch (Exception e) {
            return "redirect:add";
        }
        invitationService.addUserToRoom(user.getId(),chatroom.getId());
        return "redirect:/app/chat";
    }

    @RequestMapping(value = "/app/chat", method = RequestMethod.GET)
    public String chatGet(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
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
        model.addAttribute("owner", chatroomService.findByRoomName(myRoom).getChatOwner().getUsername());
        return "chat";
    }

    @RequestMapping(value = "/app/rooms", method = RequestMethod.GET)
    public String myRooms(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        List<ChatroomEntity> rooms = chatroomService.findRoomsOwnedByUser(userService.findByUserName(currentPrincipalName).getId());
        model.addAttribute("rooms", rooms);

        return "myRooms";
    }

    @RequestMapping(value = "/app/rooms/roomInfo", method = RequestMethod.POST)
    public String roomDetail(@RequestParam("room") Long roomId, Model model) {

        List<UserEntity> users = userService.findRoomsUsers(roomId);
        model.addAttribute("users", users);
        model.addAttribute("roomId", roomId);
        return "roomDetails";
    }

    @RequestMapping("/app/rooms/removeUser")
    public String removeUser(@RequestParam("roomId") Long roomId, @RequestParam("user") Long user){

        userService.removeUserFromRoom(user,roomId);

        return "403";
    }

    @RequestMapping(value = "/app/rooms/delete", method = RequestMethod.POST)
    public String deleteTest(@RequestParam("roomId") Long roomId){

        chatroomRepository.deleteById(roomId);

        return "403";
    }
}
