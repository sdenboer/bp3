package com.example.bp3.service.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import lombok.Setter;
import lombok.Getter;

/**
 * @author Thom
 */

@Setter
@Getter
public class Student extends Account {
    private int leerjaar;
    private Opleiding opleiding;
    @SerializedName("studentEmail")
    private String email;
    private String naam;

    public Student(String email, String wachtwoord, String naam, String telefoon, int leerjaar, Opleiding opleiding) {
        super(wachtwoord, telefoon);
        this.leerjaar = leerjaar;
        this.opleiding = opleiding;
        this.email = email;
        this.naam = naam;
    }

    public boolean login (String email, String wachtwoord){
        //controleer email en wachtwoord combinatie
        //stuur boolean terug op match
        /*
        String naam, telefoon;
        int leerjaar;
        Opleiding opleiding;

        Account account = new Docent(email, wachtwoord, naam, telefoon, leerjaar, opleiding);

        currentUser = account;
        */
        return false;
    }

    public boolean toevoegenAccount(){
        //voeg account toe met informatie uit het object
        //stuur boolean terug op succes
        return false;
    }

    public boolean bewerkenAccount(String email){
        //pas account aan met de doorgegeven email met de data in het object
        //stuur boolean terug op succes
        return false;
    }
}
