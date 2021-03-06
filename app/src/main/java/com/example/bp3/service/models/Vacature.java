package com.example.bp3.service.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Vacature {

    protected int vacaturenummer;
    protected String functie, sector, periode, arbeidsvoorwaarden, soort;
    protected Bedrijf bedrijf;

    public Vacature(int vacaturenummer, String functie, String sector, String periode, String arbeidsvoorwaarden, String soort, Bedrijf bedrijf){
        this.vacaturenummer = vacaturenummer;
        this.functie = functie;
        this.sector = sector;
        this.periode = periode;
        this.arbeidsvoorwaarden = arbeidsvoorwaarden;
        this.soort = soort;
        this.bedrijf = bedrijf;
    }
}
