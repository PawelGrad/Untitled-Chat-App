package pl.coderslab.chatApp.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import pl.coderslab.chatApp.Model.Chatroom.ChatroomEntity;

import pl.coderslab.chatApp.Model.Message.MessageEntity;
import pl.coderslab.chatApp.Model.User.UserEntity;
import pl.coderslab.chatApp.Repos.ChatroomRepository;
import pl.coderslab.chatApp.Repos.UserRepository;


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



    @RequestMapping("/testDB")
    public String testDB(){

        UserEntity user = userRepository.findByUsername("Pawcik");

        ChatroomEntity room = chatroomRepository.findByRoomName("Room 1");


        user.getChatrooms().add(room);

        userRepository.save(user);


        return "403";
    }

}