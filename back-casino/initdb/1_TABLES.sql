CREATE DATABASE casino_game;

CREATE TABLE players (
                         id SERIAL PRIMARY KEY,
                         username VARCHAR(50) NOT NULL UNIQUE,
                         password_hash VARCHAR(255) NOT NULL,
                         email VARCHAR(100) NOT NULL UNIQUE,
                         token_balance INT DEFAULT 1000
);

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

CREATE TABLE games (
                       id SERIAL PRIMARY KEY,
                       player_id INT NOT NULL,
                       start_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       end_time TIMESTAMP,
                       is_game_over BOOLEAN DEFAULT FALSE,
                       FOREIGN KEY (player_id) REFERENCES players(id) ON DELETE CASCADE
);

CREATE TABLE bets (
                      id SERIAL PRIMARY KEY,
                      player_id INT NOT NULL,
                      game_id INT NOT NULL,
                      amount INT NOT NULL,
                      outcome VARCHAR(10),
                      FOREIGN KEY (player_id) REFERENCES players(id) ON DELETE CASCADE,
                      FOREIGN KEY (game_id) REFERENCES games(id) ON DELETE CASCADE
);