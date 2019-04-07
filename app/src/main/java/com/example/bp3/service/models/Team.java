package com.example.bp3.service.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Sven
 */

@Getter
@Setter
public class Team implements Serializable {

    private String teamNaam;
    @SerializedName("studentCollection")
    private ArrayList<Student> teamMembers = new ArrayList<>();

    public Team(String teamNaam) {
        this.teamNaam = teamNaam;
    }

    public void addTeamMember(Student student) {
        this.getTeamMembers().add(student);
    }

    public void removeTeamMember(Student student) {
        this.getTeamMembers().removeIf((Student s) -> (s.getEmail().equals(student.getEmail())));
    }
}
