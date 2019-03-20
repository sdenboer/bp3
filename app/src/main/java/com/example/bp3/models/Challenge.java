package com.example.bp3.models;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Challenge extends Activiteit {

    private String challengeNaam, beschrijving, reward;
    private Bedrijf bedrijf;

    public Challenge() {
    }
}
