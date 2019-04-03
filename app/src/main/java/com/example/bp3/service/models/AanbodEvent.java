package com.example.bp3.service.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Koen Franken
 */


@Getter
@Setter
public class AanbodEvent implements Serializable {

    private String naam, locatie, omschrijving, datumentijd;
    private int  eventnummer, aantalPersonen;
    @SerializedName("bedrijfBedrijfsemail")
    private Bedrijf bedrijf;
    @SerializedName("docentDocentEmail")
    private Docent docent;
    @SerializedName("soortEventsoort")
    private EventSoort soort;

    public AanbodEvent(String naam, String locatie, String datumentijd, int aantalPersonen, String omschrijving, EventSoort soort){
        this.naam = naam;
        this.locatie = locatie;
        this.datumentijd = datumentijd;
        this.aantalPersonen = aantalPersonen;
        this.omschrijving = omschrijving;
        this.soort = soort;
    }

    public int geteventnummer(){
        return eventnummer;
    }

    public Bedrijf getBedrijf(){
        return bedrijf;
    }
    public void setBedrijf(Bedrijf bedrijf){
        this.bedrijf = bedrijf;
    }
    public Docent getDocent(){
        return docent;
    }
    public void setDocent(Docent docent){
        this.docent = docent;
    }


    public String getPrettyDeadline() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMMM-yyyy HH:mm");
        LocalDateTime time =  LocalDateTime.parse (this.datumentijd.replaceAll("\\+(.*)$", ""));
        return formatter.format(time);
    }

    public String toUglyDeadline(Timestamp ts){

        return String.valueOf(ts);
    }
}
