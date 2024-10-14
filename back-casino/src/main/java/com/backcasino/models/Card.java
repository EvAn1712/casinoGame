package com.backcasino.models;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Card {
    private String suit;
    private String rank;
    private int value;
}
