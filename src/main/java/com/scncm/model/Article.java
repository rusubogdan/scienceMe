package com.scncm.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "article")
public class Article implements Serializable{

    /*no html content constructor*/
    public Article(int articleId, String title, String description, User owner,
                   int readingTime, String link, Date createdDate, long rating) {
        this.articleId = articleId;
        this.title = title;
        this.description = description;
        this.owner = owner;
        this.readingTime = readingTime;
        this.link = link;
        this.createdDate = (Timestamp) createdDate;
    }

    /* auto generated constructor will all the arguments */
    public Article(String title, String description, User owner, Set<UserArticleVote> userArticleVoteSet,
                   Set<ArticleTag> articleTags, Integer readingTime, String link, Timestamp createdDate,
                   String token, Integer htmlContentId) {
        this.title = title;
        this.description = description;
        this.owner = owner;
        this.userArticleVoteSet = userArticleVoteSet;
        this.articleTags = articleTags;
        this.readingTime = readingTime;
        this.link = link;
        this.createdDate = createdDate;
        this.token = token;
        this.htmlContentId = htmlContentId;
    }

    public Article(){}

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "article_id_seq")
    @SequenceGenerator(name = "article_id_seq", sequenceName = "article_id_seq", allocationSize = 1)
    @Column(name = "article_id")
    private Integer articleId;

    // required - but not unique !!!
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

    @JsonIgnore
    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    private Set<UserArticleVote> userArticleVoteSet;

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    @JsonManagedReference("article-articleTags")
    private Set<ArticleTag> articleTags;

    // for rating set formula and calculate rating

    // calculated in minutes - required
    @Column(name = "reading_time")
    private Integer readingTime;

    // link to the article - required - unique
    @Column(name = "link")
    private String link;

//    @Column(name = "html_content")
//    @JsonIgnore
//    @Basic(fetch = FetchType.LAZY)
//    private String htmlContent;

    @Column(name = "created_date")
    private Timestamp createdDate;

    @Column(name = "token")
    private String token;

    @Column(name = "image_link")
    private String imageLink;

    /*@OneToMany(fetch = FetchType.LAZY, mappedBy = "article", cascade = CascadeType.ALL)
    @JsonManagedReference("Article-HtmlContentId")
    private Set<HtmlContent> htmlSet;*/

    @Column(name = "html_id")
    private Integer htmlContentId;

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

//    public String getHtmlContent() {
//        return htmlContent;
//    }

//    public void setHtmlContent(String htmlContent) {
//        this.htmlContent = htmlContent;
//    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getHtmlContentId() {
        return htmlContentId;
    }

    public void setHtmlContentId(Integer htmlContentId) {
        this.htmlContentId = htmlContentId;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }
}
