package com.dezc.coffeesaleapp.models;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Client {

    private long clientId;
    private String name;
    private String lastName;
    private String secondLastName;
    private String numberWhatsApp;
    private String email;
    @Exclude
    private String password;
    private String profilePhoto;

    public Client() {
    }

    public Client(String name, String lastname, String secondLastName, String numberWhatsApp,
                  String email) {
        this.name = name;
        this.lastName = lastname;
        this.secondLastName = secondLastName;
        this.numberWhatsApp = numberWhatsApp;
        this.email = email;
    }

    public Client(String name, String lastname, String secondLastName, String numberWhatsApp, String email, String password, String profilePhoto) {
        this.name = name;
        this.lastName = lastname;
        this.secondLastName = secondLastName;
        this.numberWhatsApp = numberWhatsApp;
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSecondLastName() {
        return secondLastName;
    }

    public void setSecondLastName(String secondLastName) {
        this.secondLastName = secondLastName;
    }

    public String getNumberWhatsApp() {
        return numberWhatsApp;
    }

    public void setNumberWhatsApp(String numberWhatsApp) {
        this.numberWhatsApp = numberWhatsApp;
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
