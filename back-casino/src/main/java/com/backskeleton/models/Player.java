package main.java.com.backskeleton.models;

import java.time.LocalDateTime;
import java.util.List;

public class Player {

        private Long id;
        private String username; // Nom d'utilisateur unique
        private String passwordHash; // Hash du mot de passe pour la sécurité
        private String email; // Email du joueur, également unique
        private int tokenBalance; // Solde des jetons virtuels du joueur
        private List<Game> games;
        private List<Bet> bets;

        public Player() {}

        public Player(String username, String passwordHash, String email, int tokenBalance) {
            this.username = username;
            this.passwordHash = passwordHash;
            this.email = email;
            this.tokenBalance = tokenBalance;
        }

        // Getters et setters
        public Long getId() {
            return id;
        }

        public void setId(Long id) {
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

        // Méthode pour ajuster le solde de jetons
        public void adjustTokenBalance(int amount) {
            this.tokenBalance += amount;
        }
}
