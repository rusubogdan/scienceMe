package com.scncm.model;

import javax.persistence.*;
import java.util.List;

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

//    @Column(name = "author")
//    private User author;

    // calculated in minutes
    @Column(name = "reading_time")
    private Integer readingTime;

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    /*@JoinTable(name = "vote",
            joinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")}
    )*/
    private List<Vote> votes;

    // link to the article
    @Column(name = "link")
    private String link;

    // todo created date
}
