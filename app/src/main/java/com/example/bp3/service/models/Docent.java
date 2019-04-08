package com.example.bp3.service.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Thom
 */

@Getter
@Setter
public class Docent extends Account {
    @SerializedName("opleiding")
    private Opleiding opleiding;
    @SerializedName("lesvakCollection")
    private ArrayList<Lesvak> lesvakken;
    @SerializedName("docentEmail")
    private String email;
    private String naam;

    public Docent(String email, String wachtwoord, String naam, String telefoon, Opleiding opleiding, ArrayList<Lesvak> lesvakken) {
        super(wachtwoord, telefoon);
        this.opleiding = opleiding;
        this.lesvakken = lesvakken;
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
