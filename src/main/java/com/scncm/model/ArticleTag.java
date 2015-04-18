package com.scncm.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

@Entity
@Table(name = "article_tag")
public class ArticleTag {
    public ArticleTag(Article article, Tag tag) {
        this.article = article;
        this.tag = tag;
    }

    public ArticleTag(){}

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "article_tag_id")
    @SequenceGenerator(name = "article_tag_id", sequenceName = "article_tag_id", allocationSize = 1)
    @Column(name = "id")
    private Integer articleTagId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "article_id")
    @JsonBackReference("article-articleTags")
    private Article article;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tag_id")
    @JsonManagedReference("tags-articleTags")
    private Tag tag;

    public Integer getArticleTagId() {
        return articleTagId;
    }

    public void setArticleTagId(Integer articleTagId) {
        this.articleTagId = articleTagId;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }
}
