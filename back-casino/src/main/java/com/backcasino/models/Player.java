package com.backcasino.models;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "players")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, nullable = false)
    private String username; // Nom d'utilisateur unique

    @Column(name = "password_hash", nullable = false)
    private String passwordHash; // Hash du mot de passe pour la sécurité

    @Column(unique = true, nullable = false)
    private String email; // Email du joueur, également unique

    @Column(name = "token_balance")
    private int tokenBalance; // Solde des jetons virtuels du joueur

    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL)
    private List<Game> games;

    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL)
    private List<Bet> bets;

    public Player() {}

    public Player(int id, String username, String passwordHash, String email, int tokenBalance) {
        this.id = id;
        this.username = username;
        this.passwordHash = passwordHash;
        this.email = email;
        this.tokenBalance = tokenBalance;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTokenBalance() {
        return tokenBalance;
    }

    public void setTokenBalance(int tokenBalance) {
        this.tokenBalance = tokenBalance;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    public List<Bet> getBets() {
        return bets;
    }

    public void setBets(List<Bet> bets) {
        this.bets = bets;
    }

    public void adjustTokenBalance(int amount) {
        this.tokenBalance += amount;
    }
}
