package pl.coderslab.chatApp.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.chatApp.Model.Chatroom.ChatroomService;
import pl.coderslab.chatApp.Model.Invitation.Invitation;
import pl.coderslab.chatApp.Model.Invitation.InvitationEntity;
import pl.coderslab.chatApp.Model.Invitation.InvitationService;
import pl.coderslab.chatApp.Model.User.UserEntity;
import pl.coderslab.chatApp.Model.User.UserService;
import pl.coderslab.chatApp.Utils.Utils;

import java.util.List;

@Controller
@RequestMapping("/app")
public class InvitationController {

    private final UserService userService;
    private final InvitationService invitationService;

    public InvitationController(UserService userService, InvitationService invitationService) {
        this.userService = userService;
        this.invitationService = invitationService;
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

    @RequestMapping(value = "/invitations/{invitationLink}", method = RequestMethod.GET)
    public String joinChannelViaLink(@PathVariable("invitationLink") String invitationLink) {
        if(invitationLink != null) {
            UserEntity userEntity = userService.findByUserName(Utils.getCurrentUser());
            InvitationEntity invitationEntity = invitationService.findByInvitationLink(invitationLink);
            if (invitationEntity != null){
                invitationService.addUserToRoom(userEntity.getId(), invitationEntity.getRoom().getId());
            }
        }
        return "redirect:/app/chat";
    }
}
