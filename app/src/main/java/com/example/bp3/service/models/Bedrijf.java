package com.example.bp3.service.models;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Thom
 */

@Getter
@Setter
public class Bedrijf extends Account {
    @SerializedName("emailContactpersoon")
    private String email_contactpersoon;
    @SerializedName("telefoonContactpersoon")
    private String telefoon_contactpersoon;
    @SerializedName("bedrijfsemail")
    private String email;
    @SerializedName("bedrijfsnaam")
    protected String naam;

    public Bedrijf(String email, String wachtwoord, String naam, String telefoon, String email_contactpersoon, String telefoon_contactpersoon) {
        super(wachtwoord, telefoon);
        this.email_contactpersoon = email_contactpersoon;
        this.telefoon_contactpersoon = telefoon_contactpersoon;
        this.naam = naam;
        this.email = email;
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
