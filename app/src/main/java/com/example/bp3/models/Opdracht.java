package com.example.bp3.models;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Opdracht extends Activiteit {

    private int leerjaar;
    private String opdrachtNaam, lesvak, eisen;
    private final Docent docent;

    public Opdracht() {
    }
}
