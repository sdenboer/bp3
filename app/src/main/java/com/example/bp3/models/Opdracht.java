package com.example.bp3.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * @author Sven
 */

@Getter
@Builder
@AllArgsConstructor
public class Opdracht extends Activiteit {

    private int leerjaar;
    private String opdrachtNaam, lesvak, eisen;
    private Docent docent;

    public Opdracht() {
    }
}
