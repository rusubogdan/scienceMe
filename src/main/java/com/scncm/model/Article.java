package com.scncm.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "article")
public class Article {

    @Id
    @GeneratedValue
    @Column(name = "article_id")
    private Integer articleId;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "content")
    private String content;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    @OneToMany(mappedBy = "article", fetch = FetchType.EAGER)
    private Set<UserArticleVote> userArticleVoteSet;

    // calculated in minutes
    @Column(name = "reading_time")
    private Integer readingTime;

    // link to the article
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

    public String getContent() {
        return content;
    }

    public void setContent(String description) {
        this.content = content;
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

    // todo created date
}
