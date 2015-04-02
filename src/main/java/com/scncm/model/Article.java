package com.scncm.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "article")
public class Article implements java.io.Serializable {

    public Article(String title, String description, User owner, Set<UserArticleVote> userArticleVoteSet, Set<ArticleTag> articleTags, Integer readingTime, String link, String htmlContent, Timestamp createdDate) {
        this.title = title;
        this.description = description;
        this.owner = owner;
        this.userArticleVoteSet = userArticleVoteSet;
        this.articleTags = articleTags;
        this.readingTime = readingTime;
        this.link = link;
        this.htmlContent = htmlContent;
        this.createdDate = createdDate;
    }

    public Article(){}

    @Override
    public String toString() {
        return "Article{" +
                "articleId=" + articleId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", owner=" + owner +
                ", userArticleVoteSet=" + userArticleVoteSet +
                ", articleTags=" + articleTags +
                ", readingTime=" + readingTime +
                ", link='" + link + '\'' +
                ", htmlContent='" + htmlContent + '\'' +
                ", createdDate=" + createdDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Article article = (Article) o;

        if (articleId != null ? !articleId.equals(article.articleId) : article.articleId != null) return false;
        if (articleTags != null ? !articleTags.equals(article.articleTags) : article.articleTags != null) return false;
        if (createdDate != null ? !createdDate.equals(article.createdDate) : article.createdDate != null) return false;
        if (description != null ? !description.equals(article.description) : article.description != null) return false;
        if (htmlContent != null ? !htmlContent.equals(article.htmlContent) : article.htmlContent != null) return false;
        if (link != null ? !link.equals(article.link) : article.link != null) return false;
        if (owner != null ? !owner.equals(article.owner) : article.owner != null) return false;
        if (readingTime != null ? !readingTime.equals(article.readingTime) : article.readingTime != null) return false;
        if (title != null ? !title.equals(article.title) : article.title != null) return false;
        if (userArticleVoteSet != null ? !userArticleVoteSet.equals(article.userArticleVoteSet) : article.userArticleVoteSet != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = articleId != null ? articleId.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (owner != null ? owner.hashCode() : 0);
        result = 31 * result + (userArticleVoteSet != null ? userArticleVoteSet.hashCode() : 0);
        result = 31 * result + (articleTags != null ? articleTags.hashCode() : 0);
        result = 31 * result + (readingTime != null ? readingTime.hashCode() : 0);
        result = 31 * result + (link != null ? link.hashCode() : 0);
        result = 31 * result + (htmlContent != null ? htmlContent.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        return result;
    }

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
    @ManyToOne
    @JoinColumn(name = "owner_id")
    @JsonManagedReference("Article-OwnerId")
    private User owner;

    @OneToMany(mappedBy = "article", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<UserArticleVote> userArticleVoteSet;

    @OneToMany(mappedBy = "article", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<ArticleTag> articleTags;

    // for rating set formula and calculate rating

    // calculated in minutes - required
    @Column(name = "reading_time")
    private Integer readingTime;

    // link to the article - required
    @Column(name = "link")
    private String link;

    @Column(name = "html_content")
    private String htmlContent;

    @Column(name = "created_date")
    private Timestamp createdDate;


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

    public String getHtmlContent() {
        return htmlContent;
    }

    public void setHtmlContent(String htmlContent) {
        this.htmlContent = htmlContent;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }
}
