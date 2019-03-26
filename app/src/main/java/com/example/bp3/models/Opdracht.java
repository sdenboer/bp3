package com.example.bp3.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Sven
 */

@Getter
@Setter
public class Opdracht extends Activiteit {

    private int leerjaar;
    private String opdrachtNaam, lesvak, eisen;
    private Docent docent;

    public Opdracht(int leerjaar, String opdrachtNaam, String lesvak, String eisen) {
        this.leerjaar = leerjaar;
        this.opdrachtNaam = opdrachtNaam;
        this.lesvak = lesvak;
        this.eisen = eisen;
    }
}
