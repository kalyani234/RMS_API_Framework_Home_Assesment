package com.bbc.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Channel {
    private String id;
    private String type;
    private String title;
    private boolean has_schedule;
    private String master_brand_id;
    private String master_brand_title;
}
