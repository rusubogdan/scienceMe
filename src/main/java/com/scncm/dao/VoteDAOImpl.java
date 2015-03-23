package com.scncm.dao;

import com.scncm.model.Vote;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class VoteDAOImpl implements VoteDAO {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    public Vote getVote(Integer voteId) {
        return (Vote) getCurrentSession().load(Vote.class, voteId);
    }
}
