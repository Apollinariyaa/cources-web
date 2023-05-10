package com.example.cources.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "shopElement")
public class ShopElement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date date;
    private String name;
    private Double cost;
    private Integer countInComplect;

    @OneToMany(mappedBy = "shopElement", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Card> cards;

    public ShopElement() {
    }

    public ShopElement(Date date, String name, Double cost, Integer countInComplect, List<Card> cards) {
        this.date = date;
        this.name = name;
        this.cost = cost;
        this.countInComplect = countInComplect;
        this.cards = cards;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public Integer getCountInComplect() {
        return countInComplect;
    }

    public void setCountInComplect(Integer countInComplect) {
        this.countInComplect = countInComplect;
    }
}
