package com.example.cources.controllers;

import com.example.cources.domain.ClientInfo;
import com.example.cources.domain.Role;
import com.example.cources.repos.ClientInfoRepo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Map;

@Controller
public class MainController {
    private final ClientInfoRepo clientRepo;

    public MainController(ClientInfoRepo clientRepo) {
        this.clientRepo = clientRepo;
    }

    @GetMapping("/")
    public String main(Map<String, Object> model) {
        if(clientRepo.findByUsername("admin@admin.com")==null)
         clientRepo.save(new ClientInfo("admin@admin.com", "admin", Role.ADMIN, true, new ArrayList<>(), new ArrayList<>()));

        return "main";
    }
}
