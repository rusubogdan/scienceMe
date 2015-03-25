package com.scncm.service;

import com.scncm.model.Tag;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Tag getTag(Integer tagId) {
        return (Tag) sessionFactory.getCurrentSession().load(Tag.class, tagId);
    }

    @Override
    public Tag getTagByName(String tagName) {
        return null;
    }

    @Override
    public List<Tag> searchTagsByName(String tagName) {
        return null;
    }
}
