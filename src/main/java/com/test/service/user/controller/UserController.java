package com.test.service.user.controller;

import com.test.service.user.model.User;
import com.test.service.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("users")
@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers(@RequestParam(value = "id", required = false) String id,
                                  @RequestParam(value = "name", required = false) String name,
                                  @RequestParam(value = "username", required = false) String username,
                                  @RequestParam(value = "surname", required = false) String surname) {
        return userService.getAllUsers(id, name, username, surname);
    }
}
