package com.bbc.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Version {
    private boolean hd;
    private String id;
    private boolean uhd;
    private String kind;
    private String type;
    private List<VersionEvent> events;
    private boolean download;
    private Duration duration;
    private Availability availability;
    private String first_broadcast;
    private String first_broadcast_date_time;
}
