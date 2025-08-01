package com.bbc.model;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Schedule {
    private Channel channel;
    private List<Element> elements;
}
