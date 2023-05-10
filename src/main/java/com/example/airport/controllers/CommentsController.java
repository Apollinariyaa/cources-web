package com.example.airport.controllers;

import com.example.airport.domain.ClientInfo;
import com.example.airport.domain.Comment;
import com.example.airport.repos.CommentRepo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Controller
@RequestMapping("/comments")
public class CommentsController {
    private final CommentRepo commentRepo;

    public CommentsController(CommentRepo commentRepo) {
        this.commentRepo = commentRepo;
    }

    @GetMapping
    public String getComments(Model model){
        model.addAttribute("comments", commentRepo.findAllByOrderByCreationDateDesc());
        return "comments";
    }

    @GetMapping("/add")
    @PreAuthorize("hasAuthority('USER')")
    public String getAddComments(Model model){
        return "add-comment";
    }

    @PostMapping("/add")
    public String getAddComments(@AuthenticationPrincipal ClientInfo user, @RequestParam String text, Model model){
        if(text.isEmpty()){
            model.addAttribute("message", "Пожалуйста, заполните поле");
            return "redirect:/add-comment";
        }
        commentRepo.save(new Comment(text, user, new Date()));
        return "redirect:/comments";
    }
}
