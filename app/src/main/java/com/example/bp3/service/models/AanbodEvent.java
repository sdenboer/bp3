package com.example.bp3.service.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AanbodEvent implements Serializable {
    private Date datumentijd;
    private String naam, locatie, omschrijving;
    private int eventnummer, aantalPersonen;
    @SerializedName("bedrijfBedrijfsemail")
    private Bedrijf bedrijf;
    @SerializedName("soortEventsoort")
    private EventSoort soort;

    public AanbodEvent(int eventnummer, String naam, String locatie, Date datumentijd, int aantalPersonen, String omschrijving, Bedrijf bedrijf, EventSoort soort){
        this.eventnummer = eventnummer;
        this.naam = naam;
        this.locatie = locatie;
        this.datumentijd = datumentijd;
        this.aantalPersonen = aantalPersonen;
        this.omschrijving = omschrijving;
        this.bedrijf = bedrijf;
        this.soort = soort;
    }
}
