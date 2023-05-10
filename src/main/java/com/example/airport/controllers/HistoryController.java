package com.example.airport.controllers;

import com.example.airport.domain.Bought;
import com.example.airport.domain.Card;
import com.example.airport.domain.ClientInfo;
import com.example.airport.repos.BoughtRepo;
import com.example.airport.repos.CardRepo;
import com.example.airport.services.MailService;
import com.example.airport.services.PdfService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/history")
public class HistoryController {

    private final PdfService pdfService;
    private final MailService mailService;

    private final CardRepo cardRepo;
    private final BoughtRepo boughtRepo;

    public HistoryController(PdfService pdfService, MailService mailService, CardRepo cardRepo, BoughtRepo boughtRepo) {
        this.pdfService = pdfService;
        this.mailService = mailService;
        this.cardRepo = cardRepo;
        this.boughtRepo = boughtRepo;
    }
    @GetMapping
    public String getHistory(@RequestParam(defaultValue = "", required = false) String message, @AuthenticationPrincipal ClientInfo user, Model model){
        List<Bought> boughts = new ArrayList<>();
        for(Card t: cardRepo.findAllByClientInfo_Id(user.getId())){
            if(t.getBought()!=null){
                boughts.add(t.getBought());
            }
        }
        if(message!=null &&!message.isEmpty())
            model.addAttribute("message", message);
        model.addAttribute("boughts", boughts);
        return "history";
    }

    @GetMapping("/{bought}")
    public String getBought(@PathVariable Bought bought, Model model){
        List<Bought> boughts = new ArrayList<>();
        boughts.add(bought);
        model.addAttribute("boughts", boughts);
        return "bought";
    }

    @PostMapping("/send/{bought}")
    public String sendBought(@PathVariable Bought bought, @AuthenticationPrincipal ClientInfo user, Model model){
        try {
            mailService.send("Чек №" +bought.getId(), pdfService.createContent(bought), user.getUsername(), null);
            return "redirect:/history?message=Message sended to email " + user.getUsername() +"";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @GetMapping("/download/{bought}")
    public String downloadBought(@PathVariable Bought bought, @AuthenticationPrincipal ClientInfo user, Model model){
        try {
            mailService.send("Чек №" + bought.getId(), "Файл чека, покажите QR на пункте выдачи.", user.getUsername(), pdfService.createQR(pdfService.createContent(bought), bought.getId()));
            return "redirect:/history?message=Check sended to email " + user.getUsername() +"";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
