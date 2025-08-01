package com.bbc.model;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Image {

    private String type;
    private String standard;
    private String promotional;
    private String promotional_with_logo;
    private String inherited_from;
}
