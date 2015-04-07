package com.scncm.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "html_content")
public class HtmlContent {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "html_article_id")
    @SequenceGenerator(name = "html_article_id", sequenceName = "html_article_id", allocationSize = 1)
    @Column(name = "id")
    private Integer htmlContentId;

    @Column(name = "html")
    private String html;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "article_id")
    @JsonBackReference("Article-HtmlContentId")
    private Article article;

    public Integer getHtmlContentId() {
        return htmlContentId;
    }

    public void setHtmlContentId(Integer htmlContentId) {
        this.htmlContentId = htmlContentId;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }
}
