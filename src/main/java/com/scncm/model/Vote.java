package com.scncm.model;

import com.scncm.helpers.VoteType;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class Vote {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer voteId;

    @Column(name = "vote_name")
    private String voteName;

    @OneToMany(mappedBy = "vote", fetch = FetchType.EAGER)
    private Set<UserArticleVote> userArticleVoteSet;

    public Integer getVoteId() {
        return voteId;
    }

    public void setVoteId(Integer voteId) {
        this.voteId = voteId;
    }

    public Set<UserArticleVote> getUserArticleVoteSet() {
        return userArticleVoteSet;
    }

    public void setUserArticleVoteSet(Set<UserArticleVote> userArticleVoteSet) {
        this.userArticleVoteSet = userArticleVoteSet;
    }

    public String getVoteName() {
        return voteName;
    }

    public void setVoteName(String voteName) {
        this.voteName = voteName;
    }
}
