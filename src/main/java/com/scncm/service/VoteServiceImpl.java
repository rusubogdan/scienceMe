package com.scncm.service;

import com.scncm.model.Vote;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class VoteServiceImpl implements VoteService {
    @Override
    public Vote getVote(Integer voteId) {
        return null;
    }
}
