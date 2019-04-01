package com.example.bp3.service.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentAanbod {

    protected String studentemail, interesse, kwaliteit;

    public StudentAanbod(String studentemail, String interesse, String kwaliteit){
        this.studentemail = studentemail;
        this.interesse = interesse;
        this.kwaliteit = kwaliteit;
    }
}
