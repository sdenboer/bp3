package com.example.bp3.models;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Sven
 */

@Getter
@Setter
public class ChallengeInschrijving extends Inschrijving {

    private final Challenge challenge;

    public ChallengeInschrijving(Team team, Challenge challenge) {
        super.team = team;
        this.challenge = challenge;
    }
}
