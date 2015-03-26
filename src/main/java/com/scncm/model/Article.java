package com.scncm.model;

import org.springframework.beans.factory.annotation.Required;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "article")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "article_id_seq")
    @SequenceGenerator(name = "article_id_seq", sequenceName = "article_id_seq", allocationSize = 1)
    @Column(name = "article_id")
    private Integer articleId;

    // required - unique ?
    @Column(name = "title")
    private String title;

    // required
    @Column(name = "description")
    private String description;

    // required
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "owner_id")
    private User owner;

    @OneToMany(mappedBy = "article", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<UserArticleVote> userArticleVoteSet;

    @OneToMany(mappedBy = "article", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<ArticleTag> articleTags;

    // calculated in minutes - required
    @Column(name = "reading_time")
    private Integer readingTime;

    // link to the article - required
    @Column(name = "link")
    private String link;

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Set<UserArticleVote> getUserArticleVoteSet() {
        return userArticleVoteSet;
    }

    public void setUserArticleVoteSet(Set<UserArticleVote> userArticleVoteSet) {
        this.userArticleVoteSet = userArticleVoteSet;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getReadingTime() {
        return readingTime;
    }

    public void setReadingTime(Integer readingTime) {
        this.readingTime = readingTime;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Set<ArticleTag> getArticleTags() {
        return articleTags;
    }

    public void setArticleTags(Set<ArticleTag> articleTags) {
        this.articleTags = articleTags;
    }

    // todo created date
}
