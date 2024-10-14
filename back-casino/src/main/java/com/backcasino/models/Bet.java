//package com.backcasino.models;
//
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.Setter;
//
//@Setter
//@Getter
//@Entity
//@Table(name = "bets")
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@DiscriminatorColumn(name = "game_type", discriminatorType = DiscriminatorType.STRING)
//public class Bet {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;
//
//    private int amount;
//    private int payout;
//
//    @ManyToOne
//    @JoinColumn(name = "player_id", nullable = false)
//    private Player player;
//
//    @ManyToOne
//    @JoinColumn(name = "roulette_game_id")
//    private RouletteGame rouletteGame;
//
//
//    public Bet() {}
//
//    public Bet(int amount, Player player, RouletteGame rouletteGame) {
//        this.amount = amount;
//        this.player = player;
//        this.rouletteGame = rouletteGame;
//    }
//
///*
//    public Bet(int amount, Player player, BlackjackGame blackjackGame) {
//        this.amount = amount;
//        this.player = player;
//        this.blackjackGame = blackjackGame;
//    }
//*/
//}