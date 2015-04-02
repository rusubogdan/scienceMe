package com.scncm.model;

import javax.persistence.*;

@Entity
@Table(name = "article_tag")
public class ArticleTag {
    public ArticleTag(Article article, Tag tag) {
        this.article = article;
        this.tag = tag;
    }

    public ArticleTag(){}

    @Override
    public String toString() {
        return "ArticleTag{" +
                "articleTagId=" + articleTagId +
                ", article=" + article +
                ", tag=" + tag +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ArticleTag that = (ArticleTag) o;

        if (article != null ? !article.equals(that.article) : that.article != null) return false;
        if (articleTagId != null ? !articleTagId.equals(that.articleTagId) : that.articleTagId != null) return false;
        if (tag != null ? !tag.equals(that.tag) : that.tag != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = articleTagId != null ? articleTagId.hashCode() : 0;
        result = 31 * result + (article != null ? article.hashCode() : 0);
        result = 31 * result + (tag != null ? tag.hashCode() : 0);
        return result;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "article_tag_id")
    @SequenceGenerator(name = "article_tag_id", sequenceName = "article_tag_id", allocationSize = 1)
    @Column(name = "id")
    private Integer articleTagId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "article_id")
    private Article article;

    @ManyToOne(cascade = CascadeType.ALL)
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
