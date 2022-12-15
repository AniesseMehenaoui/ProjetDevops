package com.kenkogroup.kenko.jwt;

public class JwtResponse {

    private String userName; // le mail dans mon cas


    public JwtResponse(String userName) {
        //contient le mail de l'utilisateur qui est connecté

        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
