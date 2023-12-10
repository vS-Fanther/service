package com.test.service.user.service.serviceImpl;

import com.test.service.user.model.User;
import com.test.service.user.repository.UserRepository;
import com.test.service.user.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers(String id, String name, String username, String surname) {
        User requestUser = User
                .builder()
                .id(id)
                .name(name)
                .surname(username)
                .username(surname)
                .build();
        return userRepository.getAllUsers(requestUser);
    }
}
