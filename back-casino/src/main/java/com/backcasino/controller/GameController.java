package com.backcasino.controller;

import com.backcasino.DTO.GameCreationRequest;
import com.backcasino.models.Game;
import com.backcasino.models.Player;
import com.backcasino.models.Bet;
import com.backcasino.DTO.GameDTO;
import com.backcasino.services.GameService;
import com.backcasino.services.PlayerService;
import com.backcasino.services.BetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<GameDTO> createGame(@RequestBody GameCreationRequest request) {
        System.out.println("Player ID: " + request.getPlayerId());
        Game game = gameService.createGame(request.getPlayerId(), request.getBetAmount());
        GameDTO gamedto = new GameDTO(game);
        return ResponseEntity.ok(gamedto);
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
