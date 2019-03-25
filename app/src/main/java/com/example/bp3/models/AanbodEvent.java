package com.example.bp3.models;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AanbodEvent{
    private Date datumentijd;
    private String naam, locatie, omschrijving, email;
    private int eventnummer, aantalPersonen;

    public AanbodEvent(int eventnummer, String naam, String locatie, Date datumentijd, int aantalPersonen, String omschrijving, String email){
        this.eventnummer = eventnummer;
        this.naam = naam;
        this.locatie = locatie;
        this.datumentijd = datumentijd;
        this.aantalPersonen = aantalPersonen;
        this.omschrijving = omschrijving;
        this.email = email;
    }
}
