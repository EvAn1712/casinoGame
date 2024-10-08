CREATE DATABASE casino_game;
-- Table des joueurs
CREATE TABLE players (
                         id SERIAL PRIMARY KEY,
                         username VARCHAR(50) NOT NULL UNIQUE,
                         password_hash VARCHAR(255) NOT NULL,
                         email VARCHAR(100) NOT NULL UNIQUE,
                         token_balance INT DEFAULT 1000 -- Solde initial de jetons
);

-- Table des statistiques des joueurs
CREATE TABLE player_statistics (
                                   id SERIAL PRIMARY KEY,
                                   player_id INT NOT NULL UNIQUE,
                                   games_played INT DEFAULT 0,
                                   games_won INT DEFAULT 0,
                                   games_lost INT DEFAULT 0,
                                   total_tokens INT DEFAULT 1000, -- Solde initial de jetons
                                   total_bets INT DEFAULT 0,
                                   FOREIGN KEY (player_id) REFERENCES players(id) ON DELETE CASCADE
);

-- Table des jeux (générale pour tous les types de jeux)
CREATE TABLE games (
                       id SERIAL PRIMARY KEY,
                       player_id INT NOT NULL,
                       game_type VARCHAR(20) NOT NULL, -- Type de jeu (par exemple, "Blackjack", "Roulette")
                       start_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       end_time TIMESTAMP,
                       result VARCHAR(10), -- "WIN" ou "LOSE"
                       winning_number INT, -- Numéro gagnant pour la roulette (NULL pour d'autres jeux)
                       FOREIGN KEY (player_id) REFERENCES players(id) ON DELETE CASCADE
);

-- Table des paris (générale pour tous les jeux)
CREATE TABLE bets (
                      id SERIAL PRIMARY KEY,
                      player_id INT NOT NULL,
                      game_id INT NOT NULL,
                      amount INT NOT NULL,             -- Montant du pari
                      outcome VARCHAR(10),             -- "WIN" ou "LOSE"
                      FOREIGN KEY (player_id) REFERENCES players(id) ON DELETE CASCADE,
                      FOREIGN KEY (game_id) REFERENCES games(id) ON DELETE CASCADE
);