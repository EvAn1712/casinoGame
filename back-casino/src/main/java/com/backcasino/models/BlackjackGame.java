package com.backcasino.models;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class BlackjackGame {
    private List<Card> playerHand;
    private List<Card> dealerHand;
    private int playerScore;
    private int dealerScore;
    private boolean isGameOver;


    public BlackjackGame() {
        this.playerHand = new ArrayList<>();
        this.dealerHand = new ArrayList<>();
        this.playerScore = 0;
        this.dealerScore = 0;
        this.isGameOver = false;
    }


}
