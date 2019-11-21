package pl.coderslab.chatApp.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.chatApp.Model.Chatroom.ChatroomEntity;

import pl.coderslab.chatApp.Model.Message.MessageEntity;
import pl.coderslab.chatApp.Model.Message.MessageService;
import pl.coderslab.chatApp.Model.User.UserEntity;
import pl.coderslab.chatApp.Model.User.UserService;
import pl.coderslab.chatApp.Repos.ChatroomRepository;
import pl.coderslab.chatApp.Repos.UserRepository;


@Controller
public class MainController {

    private final ChatroomRepository chatroomRepository;
    private final UserRepository userRepository;
    private final UserService userService;
    private final MessageService messageService;

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    public MainController(ChatroomRepository chatroomRepository, UserRepository userRepository, UserService userService, MessageService messageService) {
        this.chatroomRepository = chatroomRepository;
        this.userRepository = userRepository;
        this.userService = userService;
        this.messageService = messageService;
    }

//    @RequestMapping(value = "/test", method = RequestMethod.GET)
//    public String addUser() {
//        MessageEntity msg = new MessageEntity();
//        msg.setSender("HONKER");
//        msg.setContent("HONK!");
//        msg.setType(MessageEntity.MessageType.CHAT);
//
//        messagingTemplate.convertAndSendToUser("Pawcik","/queue/Room 1", msg);
//        return "403";
//    }
//
//
//
//    @RequestMapping("/testDB")
//    public String testDB(){
//
//        UserEntity user = userRepository.findByUsername("Pawcik");
//
//        ChatroomEntity room = chatroomRepository.findByRoomName("Room 1");
//
//
//        user.getChatrooms().add(room);
//
//        userRepository.save(user);
//
//
//        return "403";
//    }
//
//    @RequestMapping("/removeUser")
//    public String removeUser(){
//
//        userService.removeUserFromRoom(227L,1L);
//
//        return "403";
//    }
//
//    @RequestMapping("/getMessages")
//    public String messegaTest(){
//
//        System.out.println(messageService.findChatroomMessages(1L).size());
//        System.out.println(messageService.findChatroomMessages(2L).size());
//        System.out.println(messageService.findChatroomMessages(3L).size());
//
//        return "403";
//    }

    @RequestMapping(value = "/deleteTest", method = RequestMethod.GET)
    public String deleteTest(){

        chatroomRepository.deleteById(24L);

        return "403";
    }



}