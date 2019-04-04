package com.example.bp3.service.models;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Account {
    @SerializedName(value="studentEmail", alternate = {"bedrijfsemail", "docentEmail"})
    protected String email;
    @SerializedName("wachtwoord")
    protected String wachtwoord;
    @SerializedName(value = "naam", alternate = {"bedrijfsnaam"} )
    protected  String naam;
    @SerializedName("telefoon")
    protected  String telefoon;

    public Account(String email, String wachtwoord, String naam, String telefoon) {
        this.email = email;
        this.wachtwoord = wachtwoord;
        this.naam = naam;
        this.telefoon = telefoon;
    }

    public abstract boolean login(String email, String wachtwoord);
    public abstract boolean toevoegenAccount();

    public abstract boolean bewerkenAccount(String email);
    public static Account currentUser;
}

