package com.kovalenko.service.user;

import com.kovalenko.entity.user.User;

import java.util.List;

public interface UserService {

    User getUserByLogin(String login);
    void register(User user);
}
