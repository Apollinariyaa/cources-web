package com.example.airport.controllers;

import com.example.airport.domain.ClientInfo;
import com.example.airport.domain.Role;
import com.example.airport.repos.ClientInfoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private ClientInfoRepo userRepo;

    @GetMapping("/registration")
    public String registration(Model model) {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(ClientInfo user, Map<String, Object> model) {
        ClientInfo userFromDb = userRepo.findByUsername(user.getUsername());

        if (userFromDb != null) {
            model.put("message", "Данный логин уже занят!");
            return "registration";
        }

        user.setRole(Role.USER);
        userRepo.save(user);

        return "redirect:/login";
    }
}
