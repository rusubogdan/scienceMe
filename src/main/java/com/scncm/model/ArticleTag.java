package com.scncm.model;

import javax.persistence.*;

@Entity
@Table(name = "article_tag")
public class ArticleTag {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "article_tag_id")
    @SequenceGenerator(name = "article_tag_id", sequenceName = "article_tag_id", allocationSize = 1)
    @Column(name = "id")
    private Integer articleTagId;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "article_id")
    private Article article;

    // annotations that ends with one will have the eager fetch strategy
    // the ones that ends with many will have the lazy fetch strategy
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "tag_id")
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
