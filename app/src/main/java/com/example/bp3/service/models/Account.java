package com.example.bp3.service.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Account {
    @SerializedName("wachtwoord")
    protected String wachtwoord;
    @SerializedName("telefoon")
    protected  String telefoon;

    public Account(String wachtwoord, String telefoon) {
        this.wachtwoord = wachtwoord;
        this.telefoon = telefoon;
    }

    public abstract boolean login(String email, String wachtwoord);
    public abstract boolean toevoegenAccount();

    public abstract boolean bewerkenAccount(String email);
    public static Account currentUser;
}

