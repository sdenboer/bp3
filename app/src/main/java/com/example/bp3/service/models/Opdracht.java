package com.example.bp3.service.models;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Sven
 */

@Getter
@Setter
public class Opdracht extends Activiteit {

    private int leerjaar;
    private int opdrachtId;
    private String opdrachtNaam, lesvak, eisen;
    @SerializedName("docentEmail")
    private Docent docent;

    public Opdracht(int leerjaar, String opdrachtNaam, String lesvak, String eisen, int aantStudMin, int aantStudMax, Docent docent, String deadline) {
        this.leerjaar = leerjaar;
        this.opdrachtNaam = opdrachtNaam;
        this.lesvak = lesvak;
        this.eisen = eisen;
        super.aantStudMax = aantStudMax;
        super.aantStudMin = aantStudMin;
        this.docent = docent;
        super.deadline = deadline;
    }
}
