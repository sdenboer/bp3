package com.example.bp3.models;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Sven
 */

@Getter
@Setter
public class OpdrachtAanbod {

    private Opdracht opdracht;
    private Bedrijf bedrijf;
    private String beschrijving;

    public OpdrachtAanbod(Opdracht opdracht, Bedrijf bedrijf, String beschrijving) {
        this.opdracht = opdracht;
        this.bedrijf = bedrijf;
        this.beschrijving = beschrijving;
    }
}
