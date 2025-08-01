package com.bbc.model;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VersionEvent {

    private String name;
    private int offset;
    private String system;
}
