package com.example.bp3.models;

import lombok.Setter;
import lombok.Getter;

@Setter
@Getter

public class Opleiding {
    private String onderwijsinstelling, opleidingsnaam, niveau;
    private ArrayList<Lesvak> lesvakken;
}
