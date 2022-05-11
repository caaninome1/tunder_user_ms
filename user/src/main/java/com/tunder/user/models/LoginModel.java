package com.tunder.user.models;



import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table (name = "login")

public class LoginModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    private Long userID;
    private String token;
    private Timestamp accessDate;
    
    public Long getUserID() {
        return userID;
    }
    public Timestamp getAccessDate() {
        return accessDate;
    }
    public void setAccessDate(Timestamp accessDate) {
        this.accessDate = accessDate;
    }
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public void setUserID(Long userID) {
        this.userID = userID;
    }


    
}
