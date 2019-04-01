package com.example.bp3.service.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import lombok.Setter;
import lombok.Getter;

@Setter
@Getter

public class Opleiding {
    private String niveau;
    private OpleidingPK opleidingPK;
    @SerializedName("lesvakCollection")
    private ArrayList<Lesvak> lesvakken;

    public class OpleidingPK {
        @SerializedName("onderwijsinstelling")
        private String onderwijsinstelling;
        @SerializedName("opleidingsnaam")
        private String opleidingsnaam;
    }

}
