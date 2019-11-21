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

import pl.coderslab.chatApp.Exceptions.UserAlreadyExistsException;
import pl.coderslab.chatApp.Model.User.User;
import pl.coderslab.chatApp.Model.User.UserEntity;

import pl.coderslab.chatApp.Model.User.UserService;
import pl.coderslab.chatApp.Repos.UserRepository;

import java.sql.SQLIntegrityConstraintViolationException;

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
        try {
            user.setEnabled(true);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userService.addUser(user);
            return "redirect:login";
        } catch (UserAlreadyExistsException e) {
            return "redirect:register";
        }
    }

    @RequestMapping(value = "/app/user/edit", method = RequestMethod.GET)
    public String editUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "editUser";
    }


    @RequestMapping(value = "/app/user/edit", method = RequestMethod.POST)
    public String processUserEditForm(@ModelAttribute User user, BindingResult result) {
        if(result.hasErrors()){
            return "redirect:/app/user/edit";
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        UserEntity userEntity = userService.findByUserName(userName);

        userEntity.setEmail(user.getEmail());
        userEntity.setPassword(passwordEncoder.encode(user.getPassword()));

        userService.updateUser(userEntity);
        return "redirect:/app/chat";

    }
}
