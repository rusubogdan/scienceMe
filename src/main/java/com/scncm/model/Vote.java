package com.scncm.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.*;
import java.util.Set;

@Entity
public class Vote {

    public Vote(String voteName, Set<UserArticleVote> userArticleVoteSet) {
        this.voteName = voteName;
        this.userArticleVoteSet = userArticleVoteSet;
    }

    public Vote(){}

    @Override
    public String toString() {
        return "Vote{" +
                "voteId=" + voteId +
                ", voteName='" + voteName + '\'' +
                ", userArticleVoteSet=" + userArticleVoteSet +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vote vote = (Vote) o;

        if (userArticleVoteSet != null ? !userArticleVoteSet.equals(vote.userArticleVoteSet) : vote.userArticleVoteSet != null)
            return false;
        if (voteId != null ? !voteId.equals(vote.voteId) : vote.voteId != null) return false;
        if (voteName != null ? !voteName.equals(vote.voteName) : vote.voteName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = voteId != null ? voteId.hashCode() : 0;
        result = 31 * result + (voteName != null ? voteName.hashCode() : 0);
        result = 31 * result + (userArticleVoteSet != null ? userArticleVoteSet.hashCode() : 0);
        return result;
    }

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
