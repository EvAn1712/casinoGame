package com.backcasino.DTO;

import com.backcasino.models.Card;
import com.backcasino.models.Deck;
import com.backcasino.models.Game;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class GameDTO {
    private Integer gameId;
    private Integer playerId;
    private List<Card> playerHand;
    private List<Card> dealerHand;
    private int playerScore;
    private int dealerScore;
    private boolean isGameOver;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Deck deck;

    public GameDTO(Game game) {
        this.gameId = game.getId();
        this.playerId = game.getPlayer().getId();
        this.playerHand = game.getPlayerHand();
        this.dealerHand = game.getDealerHand();
        this.playerScore = game.getPlayerScore();
        this.dealerScore = game.getDealerScore();
        this.isGameOver = game.isGameOver();
        this.startTime = game.getStartTime();
        this.endTime = game.getEndTime();
        this.deck = game.getDeck();
    }
}
