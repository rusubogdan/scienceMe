package com.scncm.dao;

import com.scncm.model.User;
import org.hibernate.Query;
import org.hibernate.QueryException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    public User getUser(Integer userId) {
        // todo load auxiliary data
        return (User) getCurrentSession().load(User.class, userId);
    }

    public User getUserByUsername(String username) {
        List<User> userList = new ArrayList<User>();
        Query query;
        try {
            query = getCurrentSession().createQuery("from com.scncm.model.User u where username = :username");
            query.setParameter("username", username);
            userList = query.list();
        } catch (QueryException e) {
            logger.warn(e.getMessage());
        }

        if (userList.size() > 0)
            return userList.get(0);
        else
            return null;
    }

    public User getUserByEmail(String email) {
        List<User> userList = new ArrayList<User>();
        Query query;
        try {
            query = getCurrentSession().createQuery("from com.scncm.model.User u where email = :email");
            query.setParameter("email", email);
            userList = query.list();
        } catch (QueryException e) {
            logger.warn(e.getMessage());
        }

        if (userList.size() > 0)
            return userList.get(0);
        else
            return null;
    }

    public User getUserByToken (String token) {
        List<User> userList = new ArrayList<User>();
        Query query;
        try {
            query = getCurrentSession().createQuery("from com.scncm.model.User u where token = :token");
            query.setParameter("token", token);
            userList = query.list();
        } catch (QueryException e) {
            logger.warn(e.getMessage());
        }

        if (userList.size() > 0)
            return userList.get(0);
        else
            return null;
    }

    public Integer addUser(User user) {
        Integer savedUserId = -1;

        try {
            savedUserId = (Integer) getCurrentSession().save(user);
        } catch (Exception e) {
            logger.warn(e.getMessage());
        }

        return savedUserId;
    }

    public Boolean updateUser (User user) {
        try {
            getCurrentSession().update(user);
            getCurrentSession().getTransaction().commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Boolean deleteUser (User user) {
        try {
            getCurrentSession().delete(user);
            getCurrentSession().getTransaction().commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
