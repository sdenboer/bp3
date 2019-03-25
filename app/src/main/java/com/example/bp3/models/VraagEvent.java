package com.example.bp3.models;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VraagEvent{
    private String naam, omschrijving, email;
    private int eventnummer;

    public VraagEvent(int eventnummer, String naam, String omschrijving, String email){
        this.eventnummer = eventnummer;
        this.naam = naam;
        this.omschrijving = omschrijving;
        this.email = email;
    }

}


