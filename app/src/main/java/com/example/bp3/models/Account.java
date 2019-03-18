package com.example.bp3.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Account {
    protected String email, wachtwoord, naam, telefoon;

    public Account() {

    }

    public abstract boolean login(String email, String wachtwoord);
    public abstract boolean toevoegenAccount();
    public abstract boolean bewerkenAccount(String email);
}
