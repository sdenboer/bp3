package com.example.bp3.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Tag {

    private String tag;

    public Tag(String tag) {
        this.tag = tag;
    }
}
