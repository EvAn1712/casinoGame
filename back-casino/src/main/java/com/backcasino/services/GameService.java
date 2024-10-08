package com.backcasino.services;

import com.backcasino.DAO.GameDAO;
import com.backcasino.DAO.PlayerDAO; // Ajout de l'import pour PlayerDAO
import com.backcasino.models.Game;
import com.backcasino.models.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class GameService {

    @Autowired
    private GameDAO gameDAO;

    @Autowired
    private PlayerDAO playerDAO; // Injecter PlayerDAO

    public void createGame(int playerId) { // Passer l'ID du joueur en paramètre
        // Récupérer le joueur à partir de l'ID
        Player player = playerDAO.findById(playerId).orElseThrow(() -> new IllegalArgumentException("Player not found"));

        Game newGame = new Game();
        newGame.setPlayer(player); // Définir le joueur avec l'objet Player
        newGame.setGameType("Roulette");
        newGame.setStartTime(LocalDateTime.now());
        newGame.setResult(1.0);
        newGame.setWinningNumber(17);

        // Sauvegarder le jeu dans la base de données
        gameDAO.save(newGame);
        System.out.println("Game created with ID: " + newGame.getId());
    }

    public void listAllGames() {
        Iterable<Game> games = gameDAO.findAll();
        for (Game game : games) {
            System.out.println("Game ID: " + game.getId() + " | Type: " + game.getGameType());
        }
    }
}
