package com.bbc.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Episode {
    private String id;
    private boolean live;
    private String type;
    private String title;
    private Image images;
    private Labels labels;
    private boolean signed;
    private String status;
    private String tleo_id;
    private boolean guidance;
    private String subtitle;
    private Synopses synopses;
    private List<Version> versions;
    private boolean childrens;
    private String parent_id;
    private String tleo_type;
    private List<String> categories;
    private boolean has_credits;
    private List<String> requires_ab;
    private MasterBrand master_brand;
    private String release_date;
    private List<Object> related_links;
    private String original_title;
    private String programme_type;
    private boolean audio_described;
    private boolean requires_sign_in;
    private String release_date_time;
    private String lexical_sort_letter;
    private boolean requires_tv_licence;
    private String slice_subtitle;
    private Integer parent_position;
    private String editorial_subtitle;
}
