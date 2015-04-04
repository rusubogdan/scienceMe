package com.scncm.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.*;
import java.util.Set;

@Entity
public class Vote {

    public Vote(){}

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer voteId;

    @Column(name = "vote_name")
    private String voteName;

    @OneToMany(mappedBy = "vote")
    @JsonManagedReference("Vote-UserArticleVote")
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
