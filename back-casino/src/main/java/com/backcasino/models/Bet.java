package com.backcasino.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "bets")
public class Bet {

    // Getters et setters
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private int amount;

    // Association ManyToOne avec Player
    @ManyToOne
    @JoinColumn(name = "player_id", nullable = false)
    private Player player;

    // Association ManyToOne avec Game
    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;

    public Bet() {}

    public Bet(int amount, Player player, Game game) {
        this.amount = amount;
        this.player = player;
        this.game = game;
    }

}
