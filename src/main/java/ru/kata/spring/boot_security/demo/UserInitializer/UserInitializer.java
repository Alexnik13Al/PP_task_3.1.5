//package ru.kata.spring.boot_security.demo.UserInitializer;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//import ru.kata.spring.boot_security.demo.model.Role;
//import ru.kata.spring.boot_security.demo.model.User;
//
//import ru.kata.spring.boot_security.demo.service.UserService;
//import java.util.HashSet;
//import java.util.Set;
//
//@Component
//public class UserInitializer implements CommandLineRunner {
//    private final UserService userService;
//
//    @Autowired
//    public UserInitializer(UserService userService) {
//        this.userService = userService;
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        // Create a new user
//        User user = new User();
//        user.setUsername("example_username");
//        user.setPassword("example_password");
//        user.setEmail("example@example.com");
//        user.setFirstName("John");
//        user.setLastName("Doe");
//
//        // Set the roles for the user
//        Set<Role> roles = new HashSet<>();
//        roles.add(userService.getRoleByName("ROLE_USER"));
//        user.setRoles(roles);
//
//        // Save the user
//        userService.addUser(user);
//    }
//}