package pl.coderslab.chatApp.Controllers;


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
import pl.coderslab.chatApp.Model.Chatroom;
import pl.coderslab.chatApp.Model.User;
import pl.coderslab.chatApp.Repos.ChatroomRepository;
import pl.coderslab.chatApp.Repos.UserRepository;

import java.util.List;

@Controller
public class MainController {

    private final ChatroomRepository chatroomRepository;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public MainController(ChatroomRepository chatroomRepository, UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.chatroomRepository = chatroomRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        return "addUser";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String processForm(@ModelAttribute User user, BindingResult result) {
        if(result.hasErrors()){
            return "redirect:register";
        }
        user.setEnabled(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "login";
    }

    @RequestMapping(value = "/chat", method = RequestMethod.GET)
    public String chatGet(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        List<Chatroom> rooms = chatroomRepository.findAll();
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

        List<Chatroom> rooms = chatroomRepository.findAll();
        System.out.println(rooms.size());
        model.addAttribute("myRooms", rooms);
        model.addAttribute("user", currentPrincipalName);
        model.addAttribute("room", myRoom);
        return "chat";
    }

}