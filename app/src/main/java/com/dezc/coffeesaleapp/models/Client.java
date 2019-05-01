package com.dezc.coffeesaleapp.models;

public class Client {

    private long clientId;
    private String name;
    private String lastname;
    private String secondLastname;
    private String numberWhatsapp;
    private String email;
    private String password;
    private String profilePhoto;

    public Client (){

    }

    public Client(String name, String lastname, String secondLastname, String numberWhatsapp,
                  String email) {
        this.name = name;
        this.lastname = lastname;
        this.secondLastname = secondLastname;
        this.numberWhatsapp = numberWhatsapp;
        this.email = email;
    }

    public Client(String name, String lastname, String secondLastname, String numberWhatsapp, String email, String password, String profilePhoto) {
        this.name = name;
        this.lastname = lastname;
        this.secondLastname = secondLastname;
        this.numberWhatsapp = numberWhatsapp;
        this.email = email;
        this.password = password;
        this.profilePhoto = profilePhoto;
    }

    public long getClientId() {
        return clientId;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getSecondLastname() {
        return secondLastname;
    }

    public void setSecondLastname(String secondLastname) {
        this.secondLastname = secondLastname;
    }

    public String getNumberWhatsapp() {
        return numberWhatsapp;
    }

    public void setNumberWhatsapp(String numberWhatsapp) {
        this.numberWhatsapp = numberWhatsapp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }
}
