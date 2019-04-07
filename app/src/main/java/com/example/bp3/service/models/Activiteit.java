package com.example.bp3.service.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Sven
 */
@Getter
@Setter
public class Activiteit implements Serializable {

    protected String deadline;
    @SerializedName("aantStudMin")
    protected int aantStudMin;
    protected int aantStudMax;
    @SerializedName("tagCollection")
    protected ArrayList<Tag> tags;

    public Activiteit() {
    }

    public String getPrettyDeadline() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMMM-yyyy HH:mm");
        LocalDateTime time = LocalDateTime.parse(this.deadline.replaceAll("\\+(.*)$", ""));
        return formatter.format(time);
    }

}
