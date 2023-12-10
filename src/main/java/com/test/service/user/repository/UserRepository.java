package com.test.service.user.repository;

import com.test.service.user.model.User;

import java.util.List;

public interface UserRepository {
    public List<User> getAllUsers(User user);
}
