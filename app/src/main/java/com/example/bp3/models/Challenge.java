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
public class Challenge extends Activiteit {

    private String challengeNaam, beschrijving, reward;
    private Bedrijf bedrijf;

    public Challenge() {
    }
}
