package com.zrana.springit.controller;

import com.zrana.springit.domain.Link;
import com.zrana.springit.repository.LinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/links")
public class LinkController {

    //you can autowire the property which is called as field injection
    //@autowired
    private LinkRepository linkRepository;

    //spring autowires the dependency
    @Autowired
    public LinkController(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }

    @GetMapping("/")
    public List<Link> list() {
        return linkRepository.findAll();
    }

    //CRUD
    @PostMapping("/create")
    public Link create(@ModelAttribute Link link) {
        return linkRepository.save(link);
    }

    // /links/1
    @GetMapping("/{linkId}")
    public Optional<Link> read(@PathVariable Long linkId) {
        return linkRepository.findById(linkId);
    }

    @PutMapping("/{linkId}")
    public Link update(@PathVariable Long linkId,@ModelAttribute Link link) {
        // get the link by id
        return linkRepository.save(link);
    }

    @DeleteMapping("/{linkId}")
    public void delete(@PathVariable Long linkId) {
        linkRepository.deleteById(linkId);
    }
}
