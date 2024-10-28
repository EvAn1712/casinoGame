package com.backcasino.controller;

import com.backcasino.DTO.GameActionRequestDTO;
import com.backcasino.DTO.GameCreationRequest;
import com.backcasino.DTO.GameEndRequestDTO;
import com.backcasino.models.Game;
import com.backcasino.models.Bet;
import com.backcasino.DTO.GameDTO;
import com.backcasino.models.Player;
import com.backcasino.services.GameService;
import com.backcasino.services.BetService;
import com.backcasino.services.PlayerService;
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
    private BetService betService;

    @Autowired
    private PlayerService playerService;

    @PostMapping("/create")
    public ResponseEntity<GameDTO> createGame(@RequestBody GameCreationRequest request) {
        Game game = gameService.createGame(request.getPlayerId(), request.getBetAmount());
        GameDTO gameDTO = new GameDTO(game);
        return ResponseEntity.ok(gameDTO);
    }

    @PostMapping("/hit")
    public ResponseEntity<GameDTO> playerHit(@RequestBody GameActionRequestDTO request) {
        Game game = gameService.findById(request.getGameId());
        Bet bet = betService.getBet(request.getBetId());
        gameService.playerHit(game, bet);
        return ResponseEntity.ok(new GameDTO(game));
    }

    @PostMapping("/stand")
    public ResponseEntity<GameDTO> playerStand(@RequestBody GameActionRequestDTO request) {
        Game game = gameService.findById(request.getGameId());
        Bet bet = betService.getBet(request.getBetId());
        gameService.playerStand(game, bet);
        return ResponseEntity.ok(new GameDTO(game));
    }

    @PostMapping("/surrender")
    public ResponseEntity<GameDTO> playerSurrender(@RequestBody GameEndRequestDTO request) {
        Game game = gameService.findById(request.getGameId());
        gameService.playerSurrender(game);
        return ResponseEntity.ok(new GameDTO(game));
    }

    @PostMapping("/double")
    public ResponseEntity<GameDTO> playerDouble(@RequestBody GameActionRequestDTO request) {
        Game game = gameService.findById(request.getGameId());
        gameService.playerDouble(game);
        return ResponseEntity.ok(new GameDTO(game));
    }

    @PostMapping("/end")
    public ResponseEntity<Void> endGame(@RequestBody GameEndRequestDTO request) {
        gameService.endGame(request.getGameId());
        return ResponseEntity.ok().build();
    }
}