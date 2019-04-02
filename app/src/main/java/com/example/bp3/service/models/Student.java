package com.example.bp3.service.models;

import android.os.Parcel;
import android.os.Parcelable;

import lombok.Setter;
import lombok.Getter;

@Setter
@Getter
public class Student extends Account {
    private int leerjaar;
    private Opleiding opleiding;

    public Student(String email, String wachtwoord, String naam, String telefoon, int leerjaar, Opleiding opleiding) {
        super(email, wachtwoord, naam, telefoon);
        this.leerjaar = leerjaar;
        this.opleiding = opleiding;
    }

    public boolean login (String email, String wachtwoord){
        //controleer email en wachtwoord combinatie
        //stuur boolean terug op match
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
