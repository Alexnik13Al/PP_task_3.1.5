package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.stylesheets.LinkStyle;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;
import java.util.List;


@RestController
@RequestMapping("/api/admin")
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.getUser(id));
    }

    @PostMapping("/addUser")
    public ResponseEntity<HttpStatus> addUser(@RequestBody User user) {
        userService.addUser(user);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity<HttpStatus> updateUser(@PathVariable("id") Long id,
                                                 @RequestBody User user) {
        userService.updateUser(user,id);
    return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

}
