package com.example.bp3.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OpdrachtInschrijving extends Inschrijving {

    private OpdrachtAanbod opdracht;

    public OpdrachtInschrijving(Team team, OpdrachtAanbod opdracht) {
        super.team = team;
        this.opdracht = opdracht;
    }

}
