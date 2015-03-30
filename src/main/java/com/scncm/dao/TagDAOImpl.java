package com.scncm.dao;

import com.scncm.model.Tag;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TagDAOImpl implements TagDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Tag getTag(Integer tagId) {
        // the load method wasn't good here
        // just some proxies (uncompleted objects) were loaded
        return (Tag) sessionFactory.getCurrentSession().get(Tag.class, tagId);
    }

    @Override
    public Tag getTagByName(String tagName) {
        List<Tag> tags = new ArrayList<Tag>();

        Query query = null;

        try {
            query = sessionFactory.getCurrentSession().createQuery("from Tag t where tagName = :tagName");
            query.setParameter("tagName", tagName);
            tags = query.list();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        if (tags.size() > 0) {
            return tags.get(0);
        } else {
            return null;
        }
    }

    @Override
    public List<Tag> searchTagsByName(String tagName) {
        return null;
    }
}
