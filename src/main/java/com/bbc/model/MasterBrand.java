package com.bbc.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class MasterBrand {

    private String id;
    private Titles titles;
    private String ident_id;
    private String attribution;
}
