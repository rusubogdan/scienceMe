package com.scncm.service;

import com.scncm.dao.TagDAO;
import com.scncm.model.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TagServiceImpl implements TagService{

    @Autowired
    private TagDAO tagDAO;

    @Override
    public Tag getTag(Integer tagId) {
        return tagDAO.getTag(tagId);
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
