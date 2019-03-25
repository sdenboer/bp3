package com.example.bp3.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Docent extends Account {
    private String opleiding_onderwijsinstelling, opleiding_opleidingsnaam;
    private ArrayList<Lesvak> lesvakken;

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
