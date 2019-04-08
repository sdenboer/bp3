package com.example.bp3.service.models;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

/**
 * @author Koen Franken
 */

public class Inschrijvingsnummer{
    private int aantalPersonen;
    @SerializedName("bedrijfBedrijfsemail")
    private Bedrijf bedrijf;
    @SerializedName("docentDocentEmail")
    private Docent docent;
    @SerializedName("studentStudentEmail")
    private Student student;
    @SerializedName("aanbodEventEventnummer")
    private AanbodEvent aanbodEvent;

    public Inschrijvingsnummer(int aantalPersonen, AanbodEvent aanbodEvent){
        this.aantalPersonen = aantalPersonen;
        this.aanbodEvent = aanbodEvent;
    }

    public Bedrijf getBedrijf(){
        return bedrijf;
    }
    public void setBedrijf(Bedrijf bedrijf){
        this.bedrijf = bedrijf;
    }
    public Docent getDocent(){
        return docent;
    }
    public void setDocent(Docent docent){
        this.docent = docent;
    }
    public Student getStudent(){return student;}
    public void setStudent(Student student){this.student = student;}

}
