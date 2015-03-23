package com.scncm.service;

import com.scncm.dao.UserDAO;
import com.scncm.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    public User getUser (Integer userId) {
        return userDAO.getUser(userId);
    }

    public User getUserByUsername (String username) {
        return userDAO.getUserByUsername(username);
    }

    public User getUserByEmail (String email) {
        return userDAO.getUserByEmail(email);
    }

    public User addUser (User user) {
        return userDAO.addUser(user);
    }

    public Boolean updateUser (User user) {
        return userDAO.updateUser(user);
    }

    public Boolean deleteUser (User user) {
        return userDAO.deleteUser(user);
    }
}
