package com.example.bp3.service.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AanbodEvent implements Serializable {
    private String naam, locatie, omschrijving, datumentijd;
    private int eventnummer, aantalPersonen;
    @SerializedName("bedrijfBedrijfsemail")
    private Account account;

    @SerializedName("soortEventsoort")
    private EventSoort soort;

    public AanbodEvent(int eventnummer, String naam, String locatie, String datumentijd, int aantalPersonen, String omschrijving, Account account, EventSoort soort){
        this.eventnummer = eventnummer;
        this.naam = naam;
        this.locatie = locatie;
        this.datumentijd = datumentijd;
        this.aantalPersonen = aantalPersonen;
        this.omschrijving = omschrijving;
        this.account = account;
        this.soort = soort;
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
