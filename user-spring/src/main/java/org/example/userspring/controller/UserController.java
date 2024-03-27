package org.example.userspring.controller;

import org.example.userspring.domain.User;
import org.example.userspring.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/")
    public String login(Model model) {
        return "users/login";
    }
    @GetMapping("/users/join")
    public String join(Model model) {
        return "users/join";
    }

    @PostMapping("/users/join")
    public String join(UserForm userForm){
        User user = new User();
        user.setId(userForm.getId());
        user.setPassword(userForm.getPassword());

        userService.join(user);

        return "redirect:/users/userList";
    }

    @PostMapping("/")
    public String login(UserForm userForm){
        User user = new User();
        user.setId(userForm.getId());
        user.setPassword(userForm.getPassword());

        User userFind = userService.findOne(user.getId()).get();
        if(userFind.getId().equals(userForm.getId())){
            if(userFind.getPassword().equals(userForm.getPassword())){
                return "redirect:users/userList";
            }
            else return "redirect:/";
        }
        else return "redirect:/";
    }




    @GetMapping("/users/userList")
    public String userList(Model model) {
        List<User> users = userService.findUsers();
        model.addAttribute("users", users);
        return "/users/userList";
    }
}

