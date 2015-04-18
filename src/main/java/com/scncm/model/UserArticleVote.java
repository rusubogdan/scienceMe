package com.scncm.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "user_article")
public class UserArticleVote {

    public UserArticleVote(){}

    public UserArticleVote(User user, Article article, Integer rating, Timestamp timestamp) {
        this.user = user;
        this.article = article;
        this.rating = rating;
        this.timestamp = timestamp;
    }

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

    @Column(name = "rating")
    private Integer rating;

    @Column(name = "timestamp")
    private Timestamp timestamp;

/*    @ManyToOne
    @JoinColumn(name = "vote_id", nullable = false)
    @JsonBackReference("Vote-UserArticleVote")
    private Vote vote;*/

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

//    public Vote getVote() {
//        return vote;
//    }

//    public void setVote(Vote vote) {
//        this.vote = vote;
//    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
