package com.scncm.service;

import com.scncm.model.User;

public interface UserService {

    User getUser (Integer userId);

    User getUserByUsername (String login);

    User getUserByEmail (String email);

    User addUser (User user);

    Boolean updateUser (User user);

    Boolean deleteUser (User user);
}
