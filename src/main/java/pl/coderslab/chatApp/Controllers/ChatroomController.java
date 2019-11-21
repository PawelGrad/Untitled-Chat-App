package pl.coderslab.chatApp.Controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.chatApp.Model.Chatroom.ChatroomEntity;
import pl.coderslab.chatApp.Model.Chatroom.ChatroomService;
import pl.coderslab.chatApp.Model.Invitation.InvitationService;
import pl.coderslab.chatApp.Model.User.UserEntity;
import pl.coderslab.chatApp.Model.User.UserService;
import pl.coderslab.chatApp.Utils.Utils;

import java.util.List;

@Controller
@RequestMapping("/")
public class ChatroomController {

    private final UserService userService;
    private final ChatroomService chatroomService;
    private final InvitationService invitationService;

    public ChatroomController( UserService userService, ChatroomService chatroomService, InvitationService invitationService) {
        this.userService = userService;
        this.chatroomService = chatroomService;
        this.invitationService = invitationService;
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
        UserEntity user = userService.findByUserName(Utils.getCurrentUser());
        chatroom.setChatOwner(user);
        try {
            chatroomService.add(chatroom);
        } catch (Exception e) {
            return "redirect:add";
        }
        invitationService.generateLink(chatroom.getRoomName());
        invitationService.addUserToRoom(user.getId(),chatroom.getId());
        return "redirect:/app/chat";
    }

    @RequestMapping(value = "/app/chat", method = RequestMethod.GET)
    public String chatGet(Model model) {

        List<ChatroomEntity> rooms = chatroomService.findUserRooms(userService.findByUserName(Utils.getCurrentUser()).getId());
        model.addAttribute("myRooms", rooms);
        model.addAttribute("user", Utils.getCurrentUser());
        model.addAttribute("room", "Public");
        return "chat";
    }

    @RequestMapping(value = "/app/chat", method = RequestMethod.POST)
    public String chatPost(@RequestParam("myRoom") String myRoom, Model model) {
        List<ChatroomEntity> rooms = chatroomService.findUserRooms(userService.findByUserName(Utils.getCurrentUser()).getId());
        model.addAttribute("myRooms", rooms);
        model.addAttribute("user", Utils.getCurrentUser());
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
        return "redirect:/app/rooms";
    }

    @RequestMapping(value = "/app/rooms/delete", method = RequestMethod.POST)
    public String deleteTest(@RequestParam("roomId") Long roomId){
        chatroomService.remove(roomId);
        return "redirect:/app/rooms";
    }
}
