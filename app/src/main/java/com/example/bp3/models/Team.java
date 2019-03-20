package com.example.bp3.models;

import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Team {

    private String teamNaam;
    private ArrayList<Student> teamMembers;

    public Team(){
    }

    private void addTeamMember(Student student) {
        this.getTeamMembers().add(student);
    }

    private void removeTeamMember(Student student) {
        this.getTeamMembers().removeIf((Student s) -> (s.getStudentEmail() == student.getStudentEmail()));
    }
}
