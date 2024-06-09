//package ru.kata.spring.boot_security.demo.dbinit;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import ru.kata.spring.boot_security.demo.model.Role;
//import ru.kata.spring.boot_security.demo.model.User;
//import ru.kata.spring.boot_security.demo.service.RoleService;
//import ru.kata.spring.boot_security.demo.service.UserService;
//
//import javax.annotation.PostConstruct;
//import java.util.Set;
//
//@Component
//public class DbInit {
//    private final UserService userService;
//    private final RoleService roleService;
//
//    @Autowired
//    public DbInit(UserService userService, RoleService roleService) {
//        this.userService = userService;
//        this.roleService = roleService;
//    }
//
//    @PostConstruct
//    public void init() {
//        Role admin = new Role("ROLE_ADMIN");
//        Role user = new Role("ROLE_USER");
//        roleService.addRole(admin);
//        roleService.addRole(user);
//        userService.addUser(new User("admin", "admin@admin.ru", "100", Set.of(admin),"Alex","Sidorov"));
//        userService.addUser(new User("user","user@user.ru","100", Set.of(user),"Igor", "Petrov"));
//
//
//    }
//}
