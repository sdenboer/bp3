package com.example.bp3.service.models;

import android.os.Parcel;
import android.os.Parcelable;

import lombok.Setter;
import lombok.Getter;

/**
 * @author Thom
 */

@Setter
@Getter
public class Student extends Account implements Parcelable {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
