package com.backcasino.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RouletteGame {
    private int betNumber;
    private int winningNumber;
    private String betType;
    private boolean isWin;
}
