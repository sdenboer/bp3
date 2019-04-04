package com.example.bp3.service.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import lombok.Setter;
import lombok.Getter;

/**
 * @author Thom
 */

@Setter
@Getter

public class Opleiding {
    private String niveau;
    private OpleidingPK opleidingPK;
    @SerializedName("lesvakCollection")
    private ArrayList<Lesvak> lesvakken;

    public Opleiding(String niveau, String onderwijsinstelling, String opleidingsnaam, ArrayList<Lesvak> lesvakken) {
        opleidingPK = new OpleidingPK();
        this.niveau = niveau;
        this.opleidingPK.onderwijsinstelling = onderwijsinstelling;
        this.opleidingPK.opleidingsnaam = opleidingsnaam;
        this.lesvakken = lesvakken;
    }

    public class OpleidingPK {
        @SerializedName("onderwijsinstelling")
        public String onderwijsinstelling;
        @SerializedName("opleidingsnaam")
        private String opleidingsnaam;
    }

}
