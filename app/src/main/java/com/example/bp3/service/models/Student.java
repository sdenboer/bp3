package com.example.bp3.service.models;

import android.os.Parcel;
import android.os.Parcelable;

import lombok.Setter;
import lombok.Getter;

@Setter
@Getter
public class Student extends Account implements Parcelable {
    private int leerjaar;
    private Opleiding opleiding;

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}