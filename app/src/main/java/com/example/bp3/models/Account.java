package com.example.bp3.models;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Account {
    @SerializedName(value="docentEmail", alternate = {"bedrijfsemail", "studenEmail"})
    protected String email;
    @SerializedName("wachtwoord")
    protected String wachtwoord;
    @SerializedName("naam")
    protected  String naam;
    @SerializedName("telefoon")
    protected  String telefoon;

    public Account() {
    }

    public abstract boolean login(String email, String wachtwoord);
    public abstract boolean toevoegenAccount();
    public abstract boolean bewerkenAccount(String email);
}
