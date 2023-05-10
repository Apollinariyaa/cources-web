package com.example.airport.services;

import com.example.airport.domain.Card;
import com.example.airport.domain.ClientInfo;
import com.example.airport.repos.ClientInfoRepo;
import com.example.airport.repos.CardRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private final ClientInfoRepo userRepo;
    private final CardRepo cardRepo;

    public UserService(ClientInfoRepo userRepo, CardRepo cardRepo) {
        this.userRepo = userRepo;
        this.cardRepo = cardRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepo.findByUsername(s);
    }

    public Double calculateDiscount(ClientInfo user){
        int count = 0;
        for(Card t: cardRepo.findAllByClientInfo_Id(user.getId())){
            if(t.getBought() != null){
                count++;
            }
        }
        return (double)count%15;
    }
}
