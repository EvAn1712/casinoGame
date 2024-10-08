package main.java.com.backskeleton.models;

import java.time.LocalDateTime;
import java.util.Date;


public class Game {

    private Long id;

    private Player player;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private int betAmount;

    private String result; // "WIN" ou "LOSE" - Résultat final du jeu

    public Game() {}

    public Game(Player player, LocalDateTime startTime, int betAmount) {
        this.player = player;
        this.startTime = startTime;
        this.betAmount = betAmount;
    }

    // Getters et setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public int getBetAmount() {
        return betAmount;
    }

    public void setBetAmount(int betAmount) {
        this.betAmount = betAmount;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
