package com.test.service.service;

import com.test.service.model.User;


import java.util.List;

public interface DBService {
    public List<User> findAllUsers(User user);
}
