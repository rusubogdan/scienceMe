package com.scncm.dao;

import com.scncm.model.User;

public interface UserDAO {

    User getUser (Integer userId);

    User getUserByUsername (String login);

    User getUserByEmail (String email);

    User getUserByToken (String token);

    Integer addUser(User user);

    Boolean updateUser (User user);

    Boolean deleteUser (User user);
}
