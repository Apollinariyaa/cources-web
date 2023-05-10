package com.example.cources.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "client_info")
public class ClientInfo implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "Логин не может быть пустым")
    private String username;
    @NotBlank(message = "Пароль не может быть пустым")
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    private boolean active;
    @OneToMany(mappedBy = "clientInfo", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Card> cards;

    @OneToMany(mappedBy = "clientInfo", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Comment> comments;

    public ClientInfo() {
        active=true;
    }

    public ClientInfo(String username, String password, Role role, boolean active, List<Card> cards, List<Comment> comments) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.active = active;
        this.cards = cards;
        this.comments = comments;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Card> getTickets() {
        return cards;
    }

    public void setTickets(List<Card> cards) {
        this.cards = cards;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public boolean isAdmin() {return role == Role.ADMIN;}

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isActive();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(getRole());
    }
}
