package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.demo.service.UserService;

@Controller
public class LoginController {

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

//    @Autowired
//    public void setUserService(UserService userService) {
//        this.userService = userService;
//    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

//    @PostMapping("/authenticate")
//    public String authenticateUser(@RequestParam String email, @RequestParam String password) {
//        System.out.println("Starting authentication process...");
//
//        // Querying the user
//        User user = userService.findByEmail(email);
//
//        if (user == null) {
//            System.out.println("User not found for email: " + email);
//            throw new UsernameNotFoundException("User not found");
//        }
//
//        System.out.println("User found: " + user.getEmail());
//
//        System.out.println("Authentication successful for user: " + user.getEmail());
//
//        // Redirect to the login page
//        return "redirect:/login";
//    }
}