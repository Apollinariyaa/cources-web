package com.example.cources.controllers;

import com.example.cources.domain.ClientInfo;
import com.example.cources.domain.Role;
import com.example.cources.repos.ClientInfoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
@PreAuthorize("hasAuthority('ADMIN')")
public class UserController {
    @Autowired
    private ClientInfoRepo userRepo;

    @GetMapping
    public String getUsers(Model model){
        model.addAttribute("usrs", userRepo.findByRole(Role.USER));
        return "users";
    }

    @PostMapping("{user}")
    public String editUser(@PathVariable ClientInfo user, Model model){
        user.setActive(!user.isActive());
        userRepo.save(user);
        return "redirect:/users";
    }
}
