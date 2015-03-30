package com.scncm.dao;

import com.scncm.model.Tag;

import java.util.List;

public interface TagDAO {
    Tag getTag (Integer tagId);

    Tag getTagByName (String tagName);

    List<Tag> searchTagsByName (String tagName);
}
