package pl.coderslab.chatApp.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.chatApp.Model.Chatroom.ChatroomEntity;
import pl.coderslab.chatApp.Model.Chatroom.ChatroomService;
import pl.coderslab.chatApp.Model.Invitation.InvitationEntity;
import pl.coderslab.chatApp.Model.Invitation.InvitationService;
import pl.coderslab.chatApp.Model.User.UserEntity;
import pl.coderslab.chatApp.Model.User.UserService;

import java.util.List;

@Controller
@RequestMapping("/app")
public class InvitationController {

    private final UserService userService;
    private final ChatroomService chatroomService;
    private final InvitationService invitationService;

    public InvitationController(UserService userService, ChatroomService chatroomService, InvitationService invitationService) {
        this.userService = userService;
        this.chatroomService = chatroomService;
        this.invitationService = invitationService;
    }

    @RequestMapping("/invite")
    public String inviteUserToChat() {

        InvitationEntity invitationEntity = new InvitationEntity();
        UserEntity user1 = userService.findByUserName("admin");
        UserEntity user2 = userService.findByUserName("Pawcik");
        ChatroomEntity chatroom = chatroomService.findByRoomName("Nowyroom");

        invitationEntity.setInviter(user1);
        invitationEntity.setInvitee(user2);
        invitationEntity.setRoom(chatroom);
        invitationEntity.setAccepted(false);

        invitationService.save(invitationEntity);

        return "403";
    }
}
