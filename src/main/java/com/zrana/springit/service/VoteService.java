package com.zrana.springit.service;

import com.zrana.springit.domain.Vote;
import com.zrana.springit.repository.VoteRepository;
import org.springframework.stereotype.Service;

@Service
public class VoteService {
    private VoteRepository voteRepository;

    public VoteService(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    public Vote save(Vote vote) {
        return voteRepository.save(vote);
    }
}
