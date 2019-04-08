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
        return false;
    }

    public boolean toevoegenAccount(){
        return false;
    }

    public boolean bewerkenAccount(String email){
        return false;
    }
}
