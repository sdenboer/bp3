package com.example.bp3.service.models;

import com.google.gson.annotations.SerializedName;
import com.example.bp3.libraries.models.ExpandableGroup;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Sven
 */

@Getter
@Setter
public class Team extends ExpandableGroup<Student> {

    private String teamNaam;
    @SerializedName("studentCollection")
    private ArrayList<Student> teamMembers;

    public Team(String teamNaam, List<Student> teamMembers){
        super(teamNaam, teamMembers);
        this.teamNaam = teamNaam;
    }

    public void addTeamMember(Student student) {
        this.getTeamMembers().add(student);
    }

    public void removeTeamMember(Student student) {
        this.getTeamMembers().removeIf((Student s) -> (s.getEmail().equals(student.getEmail())));
    }
}
