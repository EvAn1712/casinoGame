package com.backcasino.services;

import com.backcasino.DAO.GameDAO;
import com.backcasino.DAO.PlayerDAO; // Ajout de l'import pour PlayerDAO
import com.backcasino.models.Card;
import com.backcasino.models.Deck;
import com.backcasino.models.Game;
import com.backcasino.models.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class GameService {

    @Autowired
    private GameDAO gameDAO;

    @Autowired
    private PlayerDAO playerDAO;

    public Game createGame(Integer playerId) {
        Player player = playerDAO.findById(playerId).orElseThrow();
        Game game = new Game();
        game.getDeck().shuffle();
        game.setPlayer(player);
        game.setStartTime(LocalDateTime.now());
        return gameDAO.save(game);
    }

    public void startGame(Game game) {
        game.getPlayerHand().add(game.getDeck().drawCard());
        game.getDealerHand().add(game.getDeck().drawCard());
        game.getPlayerHand().add(game.getDeck().drawCard());
        game.getDealerHand().add(game.getDeck().drawCard());
        game.setPlayerScore(game.getPlayerHand().stream().mapToInt(Card::getValue).sum());
        game.setDealerScore(game.getDealerHand().stream().mapToInt(Card::getValue).sum());
    }

    public void playerHit(Game game) {
        game.getPlayerHand().add(game.getDeck().drawCard());
        game.setPlayerScore(game.getPlayerHand().stream().mapToInt(Card::getValue).sum());
        checkGameStatus(game);
    }

    private void dealerHit(Game game) {
        game.getDealerHand().add(game.getDeck().drawCard());
        game.setDealerScore(game.getDealerHand().stream().mapToInt(Card::getValue).sum());
        checkGameStatus(game);
    }

    private void dealerPlay(Game game) {
        while (game.getDealerScore() < 17) {
            dealerHit(game);
        }
    }

    public void playerStand(Game game) {
        dealerPlay(game);
        checkGameStatus(game);
    }

    public void playerSurrender(Game game) {
        game.setGameOver(true);
    }

    public void playerDouble(Game game) {
        game.getPlayerHand().add(game.getDeck().drawCard());
        game.setPlayerScore(game.getPlayerHand().stream().mapToInt(Card::getValue).sum());
        playerStand(game);
    }

    private void checkGameStatus(Game game) {
        if (game.getPlayerScore() > 21) {
            game.setGameOver(true);
        } else if (game.getDealerScore() > 21) {
            game.setGameOver(true);
        } else if (game.getPlayerScore() == 21) {
            game.setGameOver(true);
        } else if (game.getDealerScore() == 21) {
            game.setGameOver(true);
        }
    }

    public Game endGame(Integer gameId) {
        Game game = gameDAO.findById(gameId).orElseThrow();
        game.setEndTime(LocalDateTime.now());
        return gameDAO.save(game);
    }


}
