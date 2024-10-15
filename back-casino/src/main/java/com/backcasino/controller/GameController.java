package com.backcasino.controller;

import com.backcasino.models.Game;
import com.backcasino.models.Player;
import com.backcasino.models.Bet;
import com.backcasino.DTO.GameDTO;
import com.backcasino.services.GameService;
import com.backcasino.services.PlayerService;
import com.backcasino.services.BetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/game")
public class GameController {

    @Autowired
    private GameService gameService;

    @Autowired
    private PlayerService playerService;

    @Autowired
    private BetService betService;

    @PostMapping("/create")
    public GameDTO createGame(@RequestParam Integer playerId, @RequestParam int betAmount) {
        Game game = gameService.createGame(playerId, betAmount);
        return new GameDTO(game);
    }

    @PostMapping("/start")
    public GameDTO startGame(@RequestParam Integer gameId) {
        Game game = gameService.findById(gameId);
        gameService.startGame(game);
        return new GameDTO(game);
    }

    @PostMapping("/hit")
    public GameDTO playerHit(@RequestParam Integer gameId) {
        Game game = gameService.findById(gameId);
        gameService.playerHit(game);
        return new GameDTO(game);
    }

    @PostMapping("/stand")
    public GameDTO playerStand(@RequestParam Integer gameId) {
        Game game = gameService.findById(gameId);
        gameService.playerStand(game);
        return new GameDTO(game);
    }

    @PostMapping("/surrender")
    public GameDTO playerSurrender(@RequestParam Integer gameId) {
        Game game = gameService.findById(gameId);
        gameService.playerSurrender(game);
        return new GameDTO(game);
    }

    @PostMapping("/double")
    public GameDTO playerDouble(@RequestParam Integer gameId) {
        Game game = gameService.findById(gameId);
        gameService.playerDouble(game);
        return new GameDTO(game);
    }

    @PostMapping("/end")
    public void endGame(@RequestParam Integer gameId) {
        gameService.endGame(gameId);
    }
}
