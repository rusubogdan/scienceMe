package com.scncm.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.*;

@Entity
@Table(name = "user_article")
public class UserArticleVote {

    @Id
    @GeneratedValue(generator = "user_article_id", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "user_article_id", sequenceName = "user_article_id", allocationSize = 1)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference("UserArticleVote-UserId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "article_id", nullable = false)
    @JsonBackReference("Article-OwnerId")
    private Article article;

    @ManyToOne
    @JoinColumn(name = "vote_id", nullable = false)
    @JsonBackReference("Vote-UserArticleVote")
    private Vote vote;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Vote getVote() {
        return vote;
    }

    public void setVote(Vote vote) {
        this.vote = vote;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
