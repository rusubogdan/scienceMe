package com.scncm.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.*;

@Entity
@Table(name = "user_article")
public class UserArticleVote {

    public UserArticleVote(User user, Article article, Vote vote) {
        this.user = user;
        this.article = article;
        this.vote = vote;
    }

    public UserArticleVote(){}

    @Override
    public String toString() {
        return "UserArticleVote{" +
                "id=" + id +
                ", user=" + user +
                ", article=" + article +
                ", vote=" + vote +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserArticleVote vote1 = (UserArticleVote) o;

        if (article != null ? !article.equals(vote1.article) : vote1.article != null) return false;
        if (id != null ? !id.equals(vote1.id) : vote1.id != null) return false;
        if (user != null ? !user.equals(vote1.user) : vote1.user != null) return false;
        if (vote != null ? !vote.equals(vote1.vote) : vote1.vote != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (article != null ? article.hashCode() : 0);
        result = 31 * result + (vote != null ? vote.hashCode() : 0);
        return result;
    }

    @Id
    @GeneratedValue(generator = "user_article_id", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "user_article_id", sequenceName = "user_article_id", allocationSize = 1)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference("UserArticleVote-UserId")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "article_id", nullable = false)
    @JsonBackReference("Article-OwnerId")
    private Article article;

    @ManyToOne(fetch = FetchType.EAGER)
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
