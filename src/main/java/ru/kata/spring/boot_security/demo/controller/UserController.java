package ru.kata.spring.boot_security.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;
import java.security.Principal;


@Controller
@RequestMapping("user")
public class UserController {

    private final UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }




    @GetMapping("")
    public String getUser(Model model, Principal principal) {
        logger.info("Attempting to retrieve user with username: {}", principal.getName());
        User user = userService.findByUsername(principal.getName());
        if (user == null) {
            logger.warn("User not found for username: {}", principal.getName());
            return "error";
        }
        model.addAttribute("users", user);
        logger.info("User retrieved successfully: {}", user);
        return "user";
    }

    //    @GetMapping("/")
//    public ModelAndView getHome() {
//        ModelAndView modelAndView = new ModelAndView("index");
//        return modelAndView;
//    }


//    @GetMapping("/admin")
//
//    public ModelAndView getAllUsers(Model model) {
//        List<User> users = userService.getAllUsers();
//        model.addAttribute("users", users);
//
//        return new ModelAndView("index1", model.asMap());
//    }


//    @GetMapping("/admin/adduser")
//
//    public ModelAndView createUserForm() {
//        ModelAndView modelAndView = new ModelAndView("adduser");
//        User user = new User();
//        Set<Role> roles = roleService.getAllRoles();
//        user.setRoles(roles);
//        modelAndView.addObject("user", user);
//        return modelAndView;
//    }

//    @PostMapping("/admin")
//
//    public String addUser(@ModelAttribute User user) {
//        userService.addUser(user);
//        return "redirect:/admin";
//    }

//    @GetMapping("/updateuser")
//    @Secured("ROLE_ADMIN")
//    public ModelAndView getEditUserForm(@PathVariable("id") Long id) {
//        User user = userService.getUser(id);
//        ModelAndView modelAndView = new ModelAndView("updateuser");
//        modelAndView.addObject("user", user);
//        return modelAndView;
//    }
//
//    @PostMapping("/admin/users/{id}")
//    @Secured("ROLE_ADMIN")
//    public ResponseEntity<User> updateUser(@PathVariable("id") Long id, @RequestBody User user) {
//        User updatedUser = userService.updateUser(user);
//        return ResponseEntity.ok(updatedUser);
//    }
//
//    @PostMapping("/admin/users/delete/{id}")
//    public String deleteUser(@PathVariable("id") Long id) {
//        userService.deleteUser(id);
//        return "redirect:/admin";
//    }
}
