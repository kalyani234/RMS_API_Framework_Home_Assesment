package com.bbc.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Element {

    private String id;
    private String scheduled_start;
    private String scheduled_end;
    private Duration duration;
    private String transmission_start;
    private String transmission_end;
    private boolean blanked;
    private boolean repeat;
    private Episode episode;
    private String episode_id;
    private String version_id;
    private String service_id;
    private String channel_title;
    private String type;
    private List<Event> events;
}
