package com.example.airport.domain;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "bought")
public class Bought {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "cardId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Card card;
    private String adress;
    private String credentials;
    private Date buyDate;

    public Bought() {
    }

    public Bought(Card card, String adress, String credentials, Date buyDate) {
        this.card = card;
        this.adress = adress;
        this.credentials = credentials;
        this.buyDate = buyDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getCredentials() {
        return credentials;
    }

    public void setCredentials(String credentials) {
        this.credentials = credentials;
    }

    public Date getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(Date buyDate) {
        this.buyDate = buyDate;
    }
}
