package com.bbc.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Availability
{

    private String start;
    private String end;
    private Remaining remaining;
}
