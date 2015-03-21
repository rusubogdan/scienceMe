package com.scncm.dao;

import com.scncm.model.User;
import org.hibernate.Query;
import org.hibernate.QueryException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

//    Logger logger = LoggerFactory.

    @Autowired
    private SessionFactory sessionFactory;

    private Session openSession() {
        return sessionFactory.getCurrentSession();
    }

    public User getUser(String username) {
        List<User> userList = new ArrayList<User>();
        Query query;
        try {
            query = openSession().createQuery("from com.scncm.model.User u where username = :username");
            query.setParameter("username", username);
            userList = query.list();
        } catch (QueryException e) {
            System.out.println(e.getMessage());
        }

        if (userList.size() > 0)
            return userList.get(0);
        else
            return null;
    }
}
