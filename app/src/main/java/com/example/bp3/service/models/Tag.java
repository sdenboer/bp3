package com.example.bp3.service.models;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Sven
 */

@Getter
@Setter
public class Tag {

    private String tag;

    public Tag(String tag) {
        this.tag = tag;
    }
}
