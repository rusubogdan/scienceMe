package com.scncm.service;

import com.scncm.model.Tag;

import java.util.List;

public interface TagService {
    Tag getTag(Integer tagId);

    Tag getTagByName (String tagName);

    List<Tag> searchTagsByName (String tagName);
}
