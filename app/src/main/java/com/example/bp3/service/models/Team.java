package com.example.bp3.service.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Sven
 */

@Getter
@Setter
public class Team {

    private String teamNaam;
    @SerializedName("studentCollection")
    private ArrayList<Student> teamMembers;

    public Team(String teamNaam, ArrayList<Student> teamMembers){
    }

    public void addTeamMember(Student student) {
        this.getTeamMembers().add(student);
    }

    public void removeTeamMember(Student student) {
        this.getTeamMembers().removeIf((Student s) -> (s.getEmail().equals(student.getEmail())));
    }
}
