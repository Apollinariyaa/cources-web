package com.example.cources.domain;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.PositiveOrZero;

@Entity
@Table(name = "card")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(optional = false)
    @JoinColumn(name = "clientId")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private ClientInfo clientInfo;
    @ManyToOne(optional = false)
    @JoinColumn(name = "elemId")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private ShopElement shopElement;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "card")
    private Bought bought;

    @PositiveOrZero
    private int Count;

    public Card() {
    }

    public Card(ClientInfo clientInfo, ShopElement shopElement, Bought bought, int count) {
        this.clientInfo = clientInfo;
        this.shopElement = shopElement;
        this.bought = bought;
        Count = count;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ClientInfo getClientInfo() {
        return clientInfo;
    }

    public void setClientInfo(ClientInfo clientInfo) {
        this.clientInfo = clientInfo;
    }

    public ShopElement getShopElement() {
        return shopElement;
    }

    public void setShopElement(ShopElement shopElement) {
        this.shopElement = shopElement;
    }

    public Bought getBought() {
        return bought;
    }

    public void setBought(Bought bought) {
        this.bought = bought;
    }

    public int getCount() {
        return Count;
    }

    public void setCount(int count) {
        Count = count;
    }
}
