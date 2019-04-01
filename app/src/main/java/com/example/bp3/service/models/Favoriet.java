package com.example.bp3.service.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Favoriet {

    protected String studentemail;
    protected int vacaturenummer;

    public Favoriet(String studentemail, int vacaturenummer){
        this.studentemail = studentemail;
        this.vacaturenummer = vacaturenummer;
    }
}
