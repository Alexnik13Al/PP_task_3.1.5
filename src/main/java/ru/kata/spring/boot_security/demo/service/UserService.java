package ru.kata.spring.boot_security.demo.service;

import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.User;
import java.util.List;

@Service
public interface UserService {

    List<User> getAllUsers();

    void addUser(User user);

    void deleteUser(Long id);

    void updateUser(User user,Long id);

    User getUser(Long id);
    User findByUsername(String username);

}

