package com.scncm.model;

import com.scncm.helpers.VoteType;

import javax.persistence.*;

@Entity
public class Vote {

    @Id
    @GeneratedValue
    @Column(name = "vote_id")
    private Integer voteId;

    // article voted
//    @Column(name = "article_id")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id", nullable = false)
    private Article article;

    //user who voted
//    @Column(name = "user_id")
//    private User user;

    @Column(name = "vote_type")
    private VoteType voteType;
}
