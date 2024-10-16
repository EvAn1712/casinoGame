package com.backcasino.services;

import com.backcasino.DAO.GameDAO;
import com.backcasino.DAO.PlayerDAO;
import com.backcasino.models.Bet;
import com.backcasino.models.Card;
import com.backcasino.models.Game;
import com.backcasino.models.Player;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class GameService {

    @Autowired
    private GameDAO gameDAO;

    @Autowired
    private PlayerDAO playerDAO;

    @Autowired
    private BetService betService;

    @Transactional
    public Game createGame(Integer playerId, int amount) {
        Player player = playerDAO.findById(playerId).orElseThrow();
        if (player.getTokenBalance() < amount) {
            throw new IllegalArgumentException("Not enough tokens to play");
        }
        player.setTokenBalance(player.getTokenBalance() - amount);
        Game game = new Game();
        game.getDeck().initializeDeck();
        game.getDeck().shuffle();
        game.setPlayer(player);
        game.setStartTime(LocalDateTime.now());
        startGame(game);
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

    private boolean checkGameStatus(Game game) {
        if (game.getPlayerScore() > 21) {
            game.setGameOver(true);
            return false;
        } else if (game.getDealerScore() > 21) {
            game.setGameOver(true);
            return true;
        } else if (game.getPlayerScore() == 21) {
            game.setGameOver(true);
            return false;
        } else if (game.getDealerScore() == 21) {
            game.setGameOver(true);
            return true;
        }
        return false;
    }

    public void winGame(Game game, Bet bet) {
        game.setGameOver(true);
        betService.resolveBet(bet, true);
        gameDAO.save(game);
    }

    public void loseGame(Game game, Bet bet) {
        game.setGameOver(true);
        betService.resolveBet(bet, false);
        gameDAO.save(game);
    }

    public void endGame(Integer gameId) {
        Game game = gameDAO.findById(gameId).orElseThrow();
        game.setEndTime(LocalDateTime.now());
        gameDAO.save(game);
    }

    public Game findById(Integer gameId) {
        return gameDAO.findById(gameId).orElseThrow(() -> new IllegalArgumentException("Game not found"));
    }
}
