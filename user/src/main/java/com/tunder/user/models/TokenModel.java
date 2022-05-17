package com.tunder.user.models;

import java.security.SecureRandom;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Base64;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "token")

public class TokenModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    private Long userID;
    private String token;
    private Timestamp creationDate;
    private int duration;

    public TokenModel() {
        this.duration = 3600000;
    }

    public Long getUserID() {
        return userID;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        // DELETE ---> generate Token
        this.token = token;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }
}
