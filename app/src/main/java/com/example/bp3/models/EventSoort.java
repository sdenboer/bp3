package com.example.bp3.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventSoort{
    private String soort;

    public EventSoort(String soort){ this.soort = soort; }

}
