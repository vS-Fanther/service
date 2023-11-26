package com.test.service.controller;

import com.test.service.model.User;
import com.test.service.service.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("users")
@RestController
public class UserController {

    DBService dbService;

    @Autowired
    public UserController(DBService dbService) {
        this.dbService = dbService;
    }

    @GetMapping
    public List<User> getAllUsers(@RequestParam(value = "id", required = false) String id,
                                  @RequestParam(value = "name", required = false) String name,
                                  @RequestParam(value = "username", required = false) String username,
                                  @RequestParam(value = "surname", required = false) String surname) {
        User requestUser = User.builder().id(id).name(name).surname(surname).username(username).build();
        return dbService.findAllUsers(requestUser);
    }
}
