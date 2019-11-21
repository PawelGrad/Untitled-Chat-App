package pl.coderslab.chatApp.Controllers;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.chatApp.Model.Chatroom.ChatroomEntity;
import pl.coderslab.chatApp.Model.Chatroom.ChatroomService;
import pl.coderslab.chatApp.Model.Invitation.Invitation;
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


        invitationService.save(invitationEntity);

        return "403";
    }

    @RequestMapping(value = "/invitations", method = RequestMethod.GET)
    public String showInvitations(Model model) {

        List<Invitation> invitations = invitationService.getUserInvitations();
        model.addAttribute("invitations", invitations);

        return "invitations";
    }

    @RequestMapping(value = "/invitations/accept", method = RequestMethod.POST)
    public String addUserToRoom(@RequestParam("userId") Long userId,@RequestParam("roomId") Long roomId,@RequestParam("invitationId") Long invitationId ) {

        invitationService.addUserToRoom(userId, roomId);
        invitationService.removeInvitation(invitationId);
        return "redirect:/app/chat";
    }
    @RequestMapping(value = "/invitations/decline", method = RequestMethod.POST)
    public String decline(@RequestParam("invitationId") Long invitationId) {

        invitationService.removeInvitation(invitationId);

        return "redirect:/app/invitations";
    }

    @RequestMapping(value = "/generateLink", method = RequestMethod.GET)
    @ResponseBody
    public String generateLink() {

        InvitationEntity invitationEntity = new InvitationEntity();
        UserEntity user1 = userService.findByUserName("admin");
        ChatroomEntity chatroom = chatroomService.findByRoomName("vbn");

        invitationEntity.setInviter(user1);
        invitationEntity.setRoom(chatroom);
        invitationEntity.setInviteLink("dasdasdasdasdasdasdasdasdas");

        invitationService.save(invitationEntity);

        return "localhost:8080/app/invitations/" +invitationEntity.getInviteLink();
    }

    @RequestMapping(value = "/invitations/{invitationLink}", method = RequestMethod.GET)
    public String joinChannelViaLink(@PathVariable("invitationLink") String invitationLink) {
        if(invitationLink != null) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userName = authentication.getName();
            UserEntity userEntity = userService.findByUserName(userName);

            InvitationEntity invitationEntity = invitationService.findByInvitationLink(invitationLink);
            if (invitationEntity != null){
                invitationService.addUserToRoom(userEntity.getId(), invitationEntity.getRoom().getId());
            }
        }
        return "redirect:/app/chat";
    }
}
