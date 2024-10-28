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
import java.util.List;

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
        Player player = playerDAO.findById(playerId).orElseThrow(() -> new IllegalArgumentException("Player not found"));
        if (player.getTokenBalance() < amount) {
            throw new IllegalArgumentException("Not enough tokens to play");
        }
        player.setTokenBalance(player.getTokenBalance() - amount);
        Game game = new Game();
        game.getDeck().initializeDeck();
        game.getDeck().shuffle();
        game.setPlayer(player);
        game.setStartTime(LocalDateTime.now());

        // Create and associate a Bet with the Game
        Bet bet = betService.placeBet(amount, player, game);
        game.setBet(bet);

        startGame(game);
        return gameDAO.save(game);
    }

    public void save(Game game) {
        gameDAO.save(game);
    }

    public void startGame(Game game) {
        game.getPlayerHand().add(game.getDeck().drawCard());
        game.getDealerHand().add(game.getDeck().drawCard());
        game.getPlayerHand().add(game.getDeck().drawCard());
        game.getDealerHand().add(game.getDeck().drawCard());
        calculatepoints(game);
    }

    public void playerHit(Game game, Bet bet) {
        game.getPlayerHand().add(game.getDeck().drawCard());
        calculatepoints(game);
        checkGameStatus(game, bet);
    }

    private void dealerHit(Game game) {
        game.getDealerHand().add(game.getDeck().drawCard());
        calculatepoints(game);
        checkGameStatus(game, null); // Pass null for bet since it's called internally
    }

    private void dealerPlay(Game game) {
        while (game.getDealerScore() < 17) {
            dealerHit(game);
        }
    }

    public void playerStand(Game game, Bet bet) {
        dealerPlay(game);
        checkGameStatus(game, bet);
    }

    public void playerSurrender(Game game) {
        game.setGameOver(true);
    }

    public void playerDouble(Game game) {
        game.getPlayerHand().add(game.getDeck().drawCard());
        game.setPlayerScore(game.getPlayerHand().stream().mapToInt(Card::getValue).sum());
        playerStand(game, null); // No bet here, as it's called internally
    }

    private boolean checkGameStatus(Game game, Bet bet) {
        if (game.getPlayerScore() > 21) {
            game.setGameOver(true);
            loseGame(game, bet);
            return false;
        } else if (game.getDealerScore() > 21) {
            game.setGameOver(true);
            winGame(game, bet);
            return true;
        } else if (game.getPlayerScore() == 21) {
            game.setGameOver(true);
            winGame(game, bet);
            return true;
        } else if (game.getDealerScore() == 21) {
            game.setGameOver(true);
            loseGame(game, bet);
            return false;
        }
        return false;
    }

    public void winGame(Game game, Bet bet) {
        game.setGameOver(true);
        betService.resolveBet(bet, "win");
        gameDAO.save(game);
    }

    public void loseGame(Game game, Bet bet) {
        game.setGameOver(true);
        betService.resolveBet(bet, "loose");
        gameDAO.save(game);
    }

    public void drawGame(Game game, Bet bet) {
        game.setGameOver(true);
        betService.resolveBet(bet, "draw");
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

    public void WinLoose(Game game, Bet bet) {
        if (game.getPlayerScore() > 21 || (game.getDealerScore() > game.getPlayerScore() && game.getDealerScore() <= 21)) {
            loseGame(game, bet);
            System.out.println("Vous avez perdu.");
        } else if (game.getDealerScore() > 21 || (game.getPlayerScore() > game.getDealerScore() && game.getPlayerScore() <= 21)) {
            winGame(game, bet);
            System.out.println("Vous avez gagné !");
        } else {
            drawGame(game, bet);
            System.out.println("Égalité.");
        }
    }

    public void calculatepoints(Game game) {
        game.setPlayerScore(game.getPlayerHand().stream().mapToInt(Card::getValue).sum());
        game.setDealerScore(game.getDealerHand().stream().mapToInt(Card::getValue).sum());
        if (HandHaveAce(game.getPlayerHand()) > 0 && game.getPlayerScore() > 21) {
            game.setPlayerScore(game.getPlayerScore() - 10);
        }
        if (HandHaveAce(game.getDealerHand()) > 0 && game.getDealerScore() > 21) {
            game.setDealerScore(game.getDealerScore() - 10);
        }
    }

    public int HandHaveAce(List<Card> hand) {
        int count = 0;
        for (Card card : hand) {
            if (card == Card.ACE_OF_CLUBS || card == Card.ACE_OF_DIAMONDS || card == Card.ACE_OF_HEARTS || card == Card.ACE_OF_SPADES) {
                count++;
            }
        }
        return count;
    }
}