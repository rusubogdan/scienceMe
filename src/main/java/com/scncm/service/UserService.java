package com.scncm.service;

import com.scncm.helpers.SignUpForm;
import com.scncm.model.User;

public interface UserService {

    User getUser (Integer userId);

    User getUserByUsername (String login);

    User getUserByEmail (String email);

    User getUserByToken (String token);

    Integer addUser (User user);

    Boolean updateUser (User user);

    Boolean deleteUser (User user);

    User fromSignUpForm (SignUpForm signUpForm, Boolean b);

    Integer getUserIdByUsername(String username);

    Boolean sendSignUpEmail (User user);
}
