package com.example.bp3.custom;

import com.example.bp3.models.Opdracht;
import com.example.bp3.models.OpdrachtAanbod;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BeschikbareOpdracht {
    private Opdracht opdracht;
    private OpdrachtAanbod opdrachtAanbod;

    public BeschikbareOpdracht(Opdracht opdracht, OpdrachtAanbod opdrachtAanbod) {
        this.opdracht = opdracht;
        this.opdrachtAanbod = opdrachtAanbod;
    }


}
