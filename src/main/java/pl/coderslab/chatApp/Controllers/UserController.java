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
import pl.coderslab.chatApp.Model.User.User;
import pl.coderslab.chatApp.Model.User.UserEntity;
import pl.coderslab.chatApp.Model.User.UserService;
import pl.coderslab.chatApp.Utils.Utils;

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
        //if(Utils.getCurrentUser() == null) {
        model.addAttribute("user", new UserEntity());
        return "addUser";
//        } else {
//            return "addUser";
//        }
    }


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String processForm(@ModelAttribute UserEntity user, BindingResult result, Model model) {
        if(result.hasErrors()){
            return "redirect:register";
        }
        try {
            user.setEnabled(true);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userService.addUser(user);
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
