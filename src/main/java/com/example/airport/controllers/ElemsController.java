package com.example.airport.controllers;

import com.example.airport.domain.*;
import com.example.airport.repos.ShopElementRepo;
import com.example.airport.repos.CardRepo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

@Controller
public class ElemsController {
    private final ShopElementRepo shopElementRepo;
    private final CardRepo cardRepo;
    public ElemsController(ShopElementRepo shopElementRepo, CardRepo cardRepo) {
        this.shopElementRepo = shopElementRepo;
        this.cardRepo = cardRepo;
    }

    @GetMapping("/elems")
    public String getRaises(Map<String, Object> model) {

        Iterable<ShopElement> elems = shopElementRepo.findByCountInComplect(0);
        model.put("elems", elems);

        return "elems";
    }

    @GetMapping("/supplies")
    public String getTours(Map<String, Object> model) {
        Iterable<ShopElement> elems = shopElementRepo.findByCountInComplectGreaterThan(0);
        model.put("elems", elems);

        return "elems";
    }

    @PostMapping("/add-to-list/{shopElement}")
    @PreAuthorize("hasAuthority('USER')")
    public String addToList(@PathVariable ShopElement shopElement, @AuthenticationPrincipal ClientInfo user, Map<String, Object> model) {
        boolean isAlreadyAdded = false;
            for(Card card : cardRepo.findAllByClientInfo_Id(user.getId())){
                if(card.getShopElement().getId() == shopElement.getId() && card.getBought()==null){
                    card.setCount(card.getCount()+1);
                    cardRepo.save(card);
                    isAlreadyAdded=true;
                    break;
                }
            }
        if(!isAlreadyAdded)
            cardRepo.save(new Card(user, shopElement, null, 1));

        return "redirect:/elems";
    }

    @GetMapping("/add-elem")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String getRaise(Map<String, Object> model) {


        return "add-elem";
    }

    @PostMapping("/add-elem")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String postRaise(@RequestParam String date, @RequestParam String name,
                            @RequestParam Double cost, @RequestParam Integer count,
                            Map<String, Object> model) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date res = new Date();
        try {
            res = formatter.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        shopElementRepo.save(new ShopElement(res, name, cost, count, new ArrayList<>()));

        return "redirect:/elems";
    }

    @GetMapping("/add-supply")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String getTour(Map<String, Object> model) {


        return "add-elem";
    }

    @GetMapping("elems/{shopElement}")
    public String getRaiseInfo(@PathVariable ShopElement shopElement, Model model){
        model.addAttribute("elem", shopElement);
        return "elem";
    }

    @GetMapping("supplies/{shopElement}")
    public String getTourInfo(@PathVariable ShopElement shopElement, Model model){
        model.addAttribute("elem", shopElement);
        return "elem";
    }
}
