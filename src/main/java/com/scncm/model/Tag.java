package com.scncm.model;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "tag")
public class Tag {

    private static final long serialVersionUID = -5527566191402296042L;

    public Tag(String tagName, Set<ArticleTag> articleTags) {
        this.tagName = tagName;
        this.articleTags = articleTags;
    }

    public  Tag(){}

    @Override
    public String toString() {
        return "Tag{" +
                "tagId=" + tagId +
                ", tagName='" + tagName + '\'' +
                ", articleTags=" + articleTags +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tag tag = (Tag) o;

        if (articleTags != null ? !articleTags.equals(tag.articleTags) : tag.articleTags != null) return false;
        if (tagId != null ? !tagId.equals(tag.tagId) : tag.tagId != null) return false;
        if (tagName != null ? !tagName.equals(tag.tagName) : tag.tagName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = tagId != null ? tagId.hashCode() : 0;
        result = 31 * result + (tagName != null ? tagName.hashCode() : 0);
        result = 31 * result + (articleTags != null ? articleTags.hashCode() : 0);
        return result;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tag_id_seq")
    @SequenceGenerator(name = "tag_id_seq", sequenceName = "tag_id_seq", allocationSize = 1)
    @Column(name = "tag_id")
    private Integer tagId;

    @Column(name = "tag_name")
    private String tagName;

    @OneToMany(mappedBy = "tag", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<ArticleTag> articleTags;

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public Set<ArticleTag> getArticleTags() {
        return articleTags;
    }

    public void setArticleTags(Set<ArticleTag> articleTags) {
        this.articleTags = articleTags;
    }
}
