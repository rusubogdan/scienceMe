package com.scncm.dao;

import com.scncm.model.Tag;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class TagDAOImpl implements TagDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Tag getTag(Integer tagId) {
        return (Tag) sessionFactory.getCurrentSession().load(Tag.class, tagId);
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
