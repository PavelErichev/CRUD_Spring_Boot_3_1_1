package ru.erichev.spring.boot.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.erichev.spring.boot.web.entity.User;
import ru.erichev.spring.boot.web.service.UserService;

import java.util.List;

@Controller
//@RequestMapping
public class UserController implements ErrorController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String showAllUser(Model model) {
        List<User> allUsers = userService.getAllUsers();
        model.addAttribute("allUs", allUsers);
        return "users";
    }

    @GetMapping("/user-create")
    public String addNewUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "user-create";
    }

    @PostMapping("/user-create")
    public String saveUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/users";
    }

    @DeleteMapping("/user-delete/{id}")              //@GetMapping("user-delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }

    @GetMapping ("/user-update/{id}")             //@GetMapping("/user-update/{id}")
    public String updateUserForm(@PathVariable("id") long id, Model model) {
        User user = userService.getUser(id);
        model.addAttribute("user",user);
        return "user-update";
    }

    @PutMapping("/user-update")
    public String updateUser(@ModelAttribute("user") User user) {
        userService.updateUser(user);
        return "redirect:/users";
    }
}