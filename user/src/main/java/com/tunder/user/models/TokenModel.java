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
@Table(name="token")

public class TokenModel {


    private static final SecureRandom secureRandom = new SecureRandom();
    private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder();


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    private Long userID;
    private String token;
    private Timestamp creationDate;
    private int duration;

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
    public TokenModel refreshToken(){
        refreshDate();
        generateToken();
        return this;
    }
    private String generateToken(){
        byte[] randomBytes = new byte[64];
        secureRandom.nextBytes(randomBytes);
        String token = base64Encoder.encodeToString(randomBytes);
        this.token = token;
        return token;
    }
    public Boolean isValidToken (String token){
        if(this.token.equals(token)) return true;
        return false;
    }
    private Timestamp refreshDate (){
        Long datetime = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(datetime);
        this.creationDate = timestamp;
        return timestamp;
    }

}
