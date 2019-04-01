package com.example.bp3.service.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Sven
 */

@Getter
@Setter
public class OpdrachtAanbod implements Serializable {

    @SerializedName("opdrachtAanbodId")
    private int id;
    @SerializedName("opdrachtVraagId")
    private Opdracht opdracht;
    @SerializedName("bedrijfEmail")
    private Bedrijf bedrijf;
    private String beschrijving;

    public OpdrachtAanbod(int id, Opdracht opdracht, Bedrijf bedrijf, String beschrijving) {
        this.id = id;
        this.opdracht = opdracht;
        this.bedrijf = bedrijf;
        this.beschrijving = beschrijving;
    }
}
