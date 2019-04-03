package com.example.bp3.service.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AanbodEvent implements Serializable {
    ArrayList<AanbodEvent> events;

    private String naam, locatie, omschrijving, datumentijd;
    private int eventnummer, aantalPersonen;
    @SerializedName("bedrijfBedrijfsemail")
    private Bedrijf bedrijf;
    @SerializedName("docentDocentEmail")
    private Docent docent;
    @SerializedName("soortEventsoort")
    private EventSoort soort;

    public AanbodEvent(int eventnummer, String naam, String locatie, String datumentijd, int aantalPersonen, String omschrijving, EventSoort soort){
        this.eventnummer = eventnummer;
        this.naam = naam;
        this.locatie = locatie;
        this.datumentijd = datumentijd;
        this.aantalPersonen = aantalPersonen;
        this.omschrijving = omschrijving;
        this.soort = soort;
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

    public ArrayList<AanbodEvent> getallEvents(){
        return events;
    }

    public void setallEvents(AanbodEvent event){
        if(events == null){
            System.out.println("Nulll");
            events = new ArrayList<>();
            events.add(event);
        } else{
            System.out.println(event.getNaam());
        }

    }
    public void setArrylist(){

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
