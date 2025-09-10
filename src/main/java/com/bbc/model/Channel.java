package com.bbc.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Channel {
    private String id;
    private String type;
    private String title;
    private boolean has_schedule;
    private String master_brand_id;
    private String master_brand_title;
}
