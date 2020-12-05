package com.zrana.springit.controller;

import com.zrana.springit.domain.Link;
import com.zrana.springit.domain.Vote;
import com.zrana.springit.repository.LinkRepository;
import com.zrana.springit.repository.VoteRepository;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class VoteController {
    private VoteRepository voteRepository;
    private LinkRepository linkRepository;

    public VoteController(VoteRepository voteRepository, LinkRepository linkRepository) {
        this.voteRepository = voteRepository;
        this.linkRepository = linkRepository;
    }

    //http://localhost:8080/vote/link/1/direction/-1/votecount/5
    @Secured({"ROLE_USER"})
    @GetMapping("/vote/link/{linkId}/direction/{direction}/votecount/{voteCount}")
    public int vote(@PathVariable Long linkId, @PathVariable short direction, @PathVariable int voteCount) {
        Optional<Link> optionalLink = linkRepository.findById(linkId);
        if(optionalLink.isPresent()) {
            Link link = optionalLink.get();
            Vote vote = new Vote(direction,link);
            voteRepository.save(vote);

            int updatedVoteCount = voteCount + direction;
            link.setVoteCount(updatedVoteCount);
            linkRepository.save(link);
            return updatedVoteCount;
        }

        return voteCount;
    }
}
