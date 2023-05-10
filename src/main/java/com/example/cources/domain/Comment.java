package com.example.cources.domain;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "Текст не может быть пустым")
    private String text;

    @ManyToOne(optional = false)
    @JoinColumn(name = "clientId")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private ClientInfo clientInfo;

    private Date creationDate;

    public Comment() {
    }

    public Comment(String text, ClientInfo clientInfo, Date creationDate) {
        this.text = text;
        this.clientInfo = clientInfo;
        this.creationDate = creationDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    public ClientInfo getClientInfo() {
        return clientInfo;
    }

    public void setClientInfo(ClientInfo clientInfo) {
        this.clientInfo = clientInfo;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
