package pl.coderslab.chatApp.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.chatApp.Model.Chatroom.ChatroomEntity;

import pl.coderslab.chatApp.Model.Message.MessageEntity;
import pl.coderslab.chatApp.Model.User.UserEntity;
import pl.coderslab.chatApp.Repos.ChatroomRepository;
import pl.coderslab.chatApp.Repos.UserRepository;

import java.util.List;

@Controller
public class MainController {

    private final ChatroomRepository chatroomRepository;
    private final UserRepository userRepository;


    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    public MainController(ChatroomRepository chatroomRepository, UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.chatroomRepository = chatroomRepository;
        this.userRepository = userRepository;

    }



    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String addUser() {
        MessageEntity msg = new MessageEntity();
        msg.setSender("HONKER");
        msg.setContent("HONK!");
        msg.setType(MessageEntity.MessageType.CHAT);

        messagingTemplate.convertAndSendToUser("Pawcik","/queue/Room 1", msg);
        return "403";
    }


    @RequestMapping(value = "/chat", method = RequestMethod.GET)
    public String chatGet(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        List<ChatroomEntity> rooms = chatroomRepository.findAll();
        System.out.println(rooms.size());
        model.addAttribute("myRooms", rooms);
        model.addAttribute("user", currentPrincipalName);
        model.addAttribute("room", "Public");
        return "chat";
    }

    @RequestMapping(value = "/chat", method = RequestMethod.POST)
    public String chatPost(@RequestParam("myRoom") String myRoom, Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

    List<ChatroomEntity> rooms = chatroomRepository.findAll();
        System.out.println(rooms.size());
        model.addAttribute("myRooms", rooms);
        model.addAttribute("user", currentPrincipalName);
        model.addAttribute("room", myRoom);
        return "chat";
}



    @RequestMapping("/testDB")
    public String testDB(){

        UserEntity user = userRepository.findByUsername("Pawcik");

        ChatroomEntity room = chatroomRepository.findByRoomName("Room 1");


        user.getChatrooms().add(room);

        userRepository.save(user);


        return "403";
    }

}