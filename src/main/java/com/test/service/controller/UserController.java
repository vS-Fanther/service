package com.test.service.controller;

import com.test.service.config.YamlReader;
import com.test.service.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("users")
@RestController
public class UserController {

    @GetMapping
    public List<User> getAllUsers() {
        System.out.println(YamlReader.getProperties());
        return null;
    }
}
