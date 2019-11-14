package pl.coderslab.chatApp.Controllers;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.coderslab.chatApp.Model.User.UserEntity;

import pl.coderslab.chatApp.Model.User.UserService;
import pl.coderslab.chatApp.Repos.UserRepository;

@Controller
@RequestMapping("/")
public class UserController {


    private final BCryptPasswordEncoder passwordEncoder;
    private final UserService userService;

    public UserController(BCryptPasswordEncoder passwordEncoder, UserService userService) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }


    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String addUser(Model model) {
        model.addAttribute("user", new UserEntity());
        return "addUser";
    }


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String processForm(@ModelAttribute UserEntity user, BindingResult result) {
        if(result.hasErrors()){
            return "redirect:register";
        }
        user.setEnabled(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.save(user);
        return "redirect:login";
    }
}
