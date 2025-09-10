package com.bbc.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Synopses {

    private String small;
    private String medium;
    private String large;
    private String editorial;
}
