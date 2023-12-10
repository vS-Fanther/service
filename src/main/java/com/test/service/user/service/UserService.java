package com.test.service.user.service;

import com.test.service.user.model.User;

import java.util.List;

public interface UserService {
    public List<User> getAllUsers(String id, String name, String username, String surname);
}
