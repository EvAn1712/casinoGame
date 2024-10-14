package com.backcasino.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "games")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "player_id") // Indique que player_id est la clé étrangère
    private Player player;

    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int dealerScore;
    private int playerScore;
    private boolean isGameOver;
    @Transient
    private List<Card> playerHand;
    @Transient
    private List<Card> dealerHand;
    @Transient
    private Deck deck;

    public Game() {
        this.playerHand = new ArrayList<>();
        this.dealerHand = new ArrayList<>();
        this.playerScore = 0;
        this.dealerScore = 0;
        this.isGameOver = false;
        this.deck = new Deck();
    }

}
