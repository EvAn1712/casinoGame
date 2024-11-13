package com.backcasino.services;

import com.backcasino.DAO.GameDAO;
import com.backcasino.DAO.PlayerDAO;
import com.backcasino.DAO.PlayerStatisticDAO;
import com.backcasino.models.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class GameService {

    @Autowired
    private GameDAO gameDAO;

    @Autowired
    private PlayerDAO playerDAO;

    @Autowired
    private BetService betService;

    @Autowired
    private PlayerService playerService;


    @Autowired
    private PlayerStatisticService playerStatisticService;

    private List<Card> playerHand = new ArrayList<>();
    private List<Card> dealerHand = new ArrayList<>();
    @Autowired
    private PlayerStatisticDAO playerStatisticDAO;

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

        Bet bet = betService.placeBet(amount, player, game);
        game.setBet(bet);

        startGame(game);
        return gameDAO.save(game);
    }

    public void save(Game game) {
        gameDAO.save(game);
    }

    public void startGame(Game game) {

        playerHand.add(game.getDeck().drawCard());
        dealerHand.add(game.getDeck().drawCard());
        playerHand.add(game.getDeck().drawCard());
        dealerHand.add(game.getDeck().drawCard());

        game.setPlayerHand(playerHand);
        game.setDealerHand(dealerHand);
        calculatepoints(game);
    }

    public void playerHit(Game game) {
        playerHand.add(game.getDeck().drawCard());
        game.setPlayerHand(playerHand);
        calculatepoints(game);
        if (game.getPlayerScore() > 21) {
            determineGameOutcome(game, game.getBet());
        }
    }

    private void dealerHit(Game game) {
        dealerHand.add(game.getDeck().drawCard());
        game.setDealerHand(dealerHand);
        calculatepoints(game);
    }

    private void dealerPlay(Game game) {
        while (game.getDealerScore() < 17 && game.getDealerScore() < game.getPlayerScore()) {
            dealerHit(game);
        }
        determineGameOutcome(game, game.getBet());
    }

    public void playerStand(Game game) {
        dealerPlay(game);
    }

    public void playerSurrender(Game game) {
        game.setGameOver(true);
        determineGameOutcome(game, game.getBet());
    }

    public void playerDouble(Game game) {
        playerHand.add(game.getDeck().drawCard());
        game.setPlayerHand(playerHand);
        calculatepoints(game);
        playerStand(game);
    }

    public void determineGameOutcome(Game game, Bet bet) {
        if (game.getPlayerScore() > 21) {
            loseGame(game, bet);
            System.out.println("Vous avez perdu.");
        } else if (game.getDealerScore() > 21 || game.getPlayerScore() > game.getDealerScore()) {
            winGame(game, bet);
            System.out.println("Vous avez gagné !");
        } else if (game.getPlayerScore() == game.getDealerScore()) {
            drawGame(game, bet);
            System.out.println("Égalité.");
        } else {
            loseGame(game, bet);
            System.out.println("Vous avez perdu.");
        }
        if (game.isGameOver()) {
            endGame(game.getId());
        }
    }

    public void winGame(Game game, Bet bet) {
        game.setGameOver(true);
        betService.resolveBet(bet, "win");
        playerStatisticService.updatePlayerStatistics(game.getPlayer().getId(),
                this.playerService.getPlayerStatistics(game.getPlayer().getId()).getGamesPlayed()+1,
                this.playerService.getPlayerStatistics(game.getPlayer().getId()).getGamesWon() +1,
                this.playerService.getPlayerStatistics(game.getPlayer().getId()).getGamesLost(),
                this.playerService.getPlayerStatistics(game.getPlayer().getId()).getTotalTokens()+bet.getAmount(),
                this.playerService.getPlayerStatistics(game.getPlayer().getId()).getTotalBets());
        gameDAO.save(game);
    }

    public void loseGame(Game game, Bet bet) {
        game.setGameOver(true);
        betService.resolveBet(bet, "lose");
        playerStatisticService.updatePlayerStatistics(game.getPlayer().getId(),
                this.playerService.getPlayerStatistics(game.getPlayer().getId()).getGamesPlayed()+1,
                this.playerService.getPlayerStatistics(game.getPlayer().getId()).getGamesWon(),
                this.playerService.getPlayerStatistics(game.getPlayer().getId()).getGamesLost()+1,
                this.playerService.getPlayerStatistics(game.getPlayer().getId()).getTotalTokens(),
                this.playerService.getPlayerStatistics(game.getPlayer().getId()).getTotalBets()+bet.getAmount());
        gameDAO.save(game);
    }

    public void drawGame(Game game, Bet bet) {
        game.setGameOver(true);
        betService.resolveBet(bet, "draw");
        playerStatisticService.updatePlayerStatistics(game.getPlayer().getId(),
                this.playerService.getPlayerStatistics(game.getPlayer().getId()).getGamesPlayed()+1,
                this.playerService.getPlayerStatistics(game.getPlayer().getId()).getGamesWon() ,
                this.playerService.getPlayerStatistics(game.getPlayer().getId()).getGamesLost(),
                this.playerService.getPlayerStatistics(game.getPlayer().getId()).getTotalTokens(),
                this.playerService.getPlayerStatistics(game.getPlayer().getId()).getTotalBets());
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