package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;


@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("")
    public String getAllUsers(Model model, Principal principal) {
        model.addAttribute("users", userService.getAllUsers());
        User user = userService.findByEmail(principal.getName());
        model.addAttribute("user",user);
        model.addAttribute("page","PAGE_ADMIN");
        model.addAttribute("newUser",new User());
        model.addAttribute("roles",roleService.getRoles());
        return "index1";
    }

    @GetMapping("/adduser")
    public String createUserForm(User user,Model model) {
        model.addAttribute("user",user);
        model.addAttribute("roles", roleService.getRoles());
        return "index1";
    }

    @PostMapping("/adduser")
    public String addUser(User user) {
        userService.addUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/updateuser/{id}")
    public String getEditUserForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getUser(id));
        model.addAttribute("roles", roleService.getRoles());
        return "updateuser";
    }

    @PostMapping("/updateuser/{id}")
    public String updateUser(@PathVariable("id") Long id, @ModelAttribute("user") User user) {
        userService.updateUser(user,id);
        return "redirect:/admin";
    }

    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}
