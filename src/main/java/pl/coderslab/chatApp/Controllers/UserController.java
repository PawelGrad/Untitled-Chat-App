package pl.coderslab.chatApp.Controllers;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.chatApp.Exceptions.UserAlreadyExistsException;
import pl.coderslab.chatApp.Model.Invitation.InvitationService;
import pl.coderslab.chatApp.Model.User.User;
import pl.coderslab.chatApp.Model.User.UserEntity;
import pl.coderslab.chatApp.Model.User.UserService;
import pl.coderslab.chatApp.Utils.Utils;

import javax.transaction.Transactional;

@Controller
@RequestMapping("/")
public class UserController {


    private final BCryptPasswordEncoder passwordEncoder;
    private final UserService userService;
    private final InvitationService invitationService;

    public UserController(BCryptPasswordEncoder passwordEncoder, UserService userService, InvitationService invitationService) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        this.invitationService = invitationService;
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


    @Transactional
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String processForm(@ModelAttribute UserEntity user, BindingResult result, Model model) {
        if(result.hasErrors()){
            return "redirect:register";
        }
        try {

            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userService.addUser(user);
            invitationService.addUserToRoom(user.getId(), 1L);
            return "redirect:login";
        } catch (UserAlreadyExistsException e) {
            model.addAttribute("exists", "User already exists");
            model.addAttribute("user", new UserEntity());
            return "addUser";
        }
    }

    @RequestMapping(value = "/app/user/edit", method = RequestMethod.GET)
    public String editUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "editUser";
    }


    @RequestMapping(value = "/app/user/edit", method = RequestMethod.POST)
    public String processUserEditForm(@RequestParam("oldPassword") String oldPassword, @RequestParam("newPassword") String newPassword, Model model) {

        UserEntity userEntity = userService.findByUserName(Utils.getCurrentUser());
        if(passwordEncoder.matches(oldPassword,userEntity.getPassword())) {
            userEntity.setPassword(passwordEncoder.encode(newPassword));
            userService.updateUser(userEntity);
            return "redirect:/app/chat";
        } else {
            model.addAttribute("mismatch", "Password doesn't match.");
            return "editUser";
        }
    }
}
