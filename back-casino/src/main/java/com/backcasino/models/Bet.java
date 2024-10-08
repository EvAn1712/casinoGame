package com.backcasino.models;

import jakarta.persistence.*;

@Entity
@Table(name = "bets")
public class Bet {

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

    // Getters et setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
