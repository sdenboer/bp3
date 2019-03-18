package com.example.bp3.models;
import java.sql.Timestamp;
import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Activiteit {

    protected Timestamp deadline;
    protected int aant_stud_min, aant_stud_max;
    protected ArrayList<Tag> tags;

    public Activiteit() {
    }
}
