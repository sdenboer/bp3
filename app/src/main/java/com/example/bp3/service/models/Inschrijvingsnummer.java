package com.example.bp3.service.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Inschrijvingsnummer{
    private int inschrijvingsnummer, aantalPersonen, eventNummer;
    private String email;

    public Inschrijvingsnummer(int inschrijvingsnummer, int aantalPersonen, int eventNummer, String email){
        this.inschrijvingsnummer = inschrijvingsnummer;
        this.aantalPersonen = aantalPersonen;
        this.eventNummer = eventNummer;
        this.email = email;
    }

}
