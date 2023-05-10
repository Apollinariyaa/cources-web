package com.example.cources.controllers;

import com.example.cources.domain.Bought;
import com.example.cources.domain.Card;
import com.example.cources.domain.ClientInfo;
import com.example.cources.repos.BoughtRepo;
import com.example.cources.repos.CardRepo;
import com.example.cources.services.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/cards")
public class CardController {

    private final CardRepo cardRepo;
    private final BoughtRepo boughtRepo;
    private final UserService userService;

    public CardController(CardRepo cardRepo, BoughtRepo boughtRepo, UserService userService) {
        this.cardRepo = cardRepo;
        this.boughtRepo = boughtRepo;
        this.userService = userService;
    }

    @GetMapping
    public String getCards(@AuthenticationPrincipal ClientInfo user, Model model){
        List<Card> lst = new ArrayList<>();
        for(Card t: cardRepo.findAllByClientInfo_Id(user.getId())){
            if(t.getBought()==null){
                lst.add(t);
            }
        }
        model.addAttribute("cards", lst);
        model.addAttribute("user", user);
        model.addAttribute("discount", userService.calculateDiscount(user));
        return "cards";
    }

    @GetMapping("/buy/{card}")
    public String getBuyCard(@PathVariable Card card, @AuthenticationPrincipal ClientInfo user, Model model){
        model.addAttribute("card", card);
        model.addAttribute("user", user);
        return "buy-card";
    }

    @PostMapping("/buy/{card}")
    public String postBuyCard(@PathVariable Card card,
                              @RequestParam String adress,
                              @RequestParam String credentials,
                              Model model){

        boughtRepo.save(new Bought(card, adress, credentials, new Date()));
        return "redirect:/cards";
    }

    @PostMapping("/delete/{card}")
    public String deleteCard(@PathVariable Card card, Model model){
        cardRepo.delete(card);
        return "redirect:/cards";
    }
}
