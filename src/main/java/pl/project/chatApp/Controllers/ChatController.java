package pl.project.chatApp.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import pl.project.chatApp.Model.Chatroom.ChatroomService;
import pl.project.chatApp.Model.Invitation.InvitationService;
import pl.project.chatApp.Model.Message.MessageEntity;
import pl.project.chatApp.Model.Message.MessageService;
import pl.project.chatApp.Model.User.UserService;

import java.util.List;

import static java.lang.String.format;

@Controller
public class ChatController {

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    private final MessageService messageService;
    private final UserService userService;
    private final ChatroomService chatroomService;
    private final InvitationService invitationService;

    public ChatController(MessageService messageService, UserService userService, ChatroomService chatroomService, InvitationService invitationService) {
        this.messageService = messageService;
        this.userService = userService;
        this.chatroomService = chatroomService;
        this.invitationService = invitationService;
    }
    @MessageMapping("/chat/{roomId}/sendMessage")
    public void sendMessage(@DestinationVariable String roomId, @Payload MessageEntity chatMessage) {

        chatMessage.setChatroom(chatroomService.findByRoomName(roomId));
        if(chatMessage.getType() == MessageEntity.MessageType.INVITE)
        {
            if(userService.findByUserName(chatMessage.getContent()) != null && !roomId.equals("Public")) {
                invitationService.addInvitation(chatMessage,roomId);
            }
        } else if (chatMessage.getType() == MessageEntity.MessageType.BAN) {

            if(userService.findByUserName(chatMessage.getContent()) != null && !roomId.equals("Public") && !(chatMessage.getContent().equals(chatMessage.getSender()))) {
                    Long userId = userService.findByUserName(chatMessage.getContent()).getId();
                    Long chatroomId = chatroomService.findByRoomName(roomId).getId();
                    userService.removeUserFromRoom(userId, chatroomId);

            }
            messagingTemplate.convertAndSend(format("/chat-room/%s", roomId), messageService.mapToDto(chatMessage));
        } else {
            messageService.save(chatMessage);
            messagingTemplate.convertAndSend(format("/chat-room/%s", roomId), messageService.mapToDto(chatMessage));
        }
    }

    @MessageMapping("/chat/{roomId}/addUser")
    public void addUser(@DestinationVariable String roomId, @Payload MessageEntity chatMessage) {
        messagingTemplate.convertAndSend(format("/chat-room/%s", roomId), chatMessage);
        List<MessageEntity> messages = messageService.findChatroomMessages(chatroomService.findByRoomName(roomId).getId());
        messages.forEach(msg -> msg.setType(MessageEntity.MessageType.CHAT));
        messages.forEach(msg -> messagingTemplate.convertAndSendToUser(chatMessage.getSender(),"/queue/" + roomId, messageService.mapToDto(msg)));
    }
}
