package com.zrana.springit.controller;

import com.zrana.springit.domain.Comment;
import com.zrana.springit.domain.Link;
import com.zrana.springit.repository.CommentRepository;
import com.zrana.springit.repository.LinkRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Optional;


@Controller
//@RequestMapping("/links")
public class LinkController {
    private static final Logger logger = LoggerFactory.getLogger(LinkController.class);

    private LinkRepository linkRepository;
    private CommentRepository commentRepository;

    public LinkController(LinkRepository linkRepository, CommentRepository commentRepository) {
        this.linkRepository = linkRepository;
        this.commentRepository = commentRepository;
    }


    @GetMapping("/")
    public String list(Model model) {
        model.addAttribute("links", linkRepository.findAll());
        return "link/list";
    }

    @GetMapping("/link/{id}")
    public String list(@PathVariable Long id,Model model) {
        Optional<Link> link = linkRepository.findById(id);
        if(link.isPresent()) {
            Link currentLink = link.get();
            Comment comment = new Comment();
            comment.setLink(currentLink);
            model.addAttribute("comment", comment);
            model.addAttribute("link", currentLink);
            model.addAttribute("success", model.containsAttribute("success"));
            return "link/view";
        }
        else {
            return "redirect:/";
        }
    }
    @GetMapping("/link/submit")
    public String newLinkForm(Model model) {
        model.addAttribute("link",new Link());
        return "link/submit";
    }
    @PostMapping("/link/submit")
    public String createLink(@Valid Link link, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        if(bindingResult.hasErrors()) {
            logger.info("Validation errors were found while submitting a new link");
            model.addAttribute("link", link);
            return "link/submit";
        }
        else {
            //save the link
            linkRepository.save(link);
            logger.info("New link was saved succesfully");
            redirectAttributes
                    .addAttribute("id",link.getId())
                    .addFlashAttribute("success",true);
            return "redirect:/link/{id}";
        }
    }

    @Secured({"ROLE_USER"})
    @PostMapping("/link/comments")
    public String addComment(@Valid Comment comment, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            logger.info("There was a problem adding a comment");
        }
        else {
            commentRepository.save(comment);
            logger.info("New comment was added successfully");
        }

        return "redirect:/link/" + comment.getLink().getId();
    }
}
