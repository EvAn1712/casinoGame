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
        GameDTO gamedto = new GameDTO(game);
        return ResponseEntity.ok(gamedto);
    }

    @PostMapping("/hit")
    public ResponseEntity<GameDTO> playerHit(@RequestBody GameDTO request) {
        Game game = gameService.findById(request.getGameId());
        game.setPlayerHand(request.getPlayerHand());
        game.setDealerHand(request.getDealerHand());
        game.setPlayerScore(request.getPlayerScore());
        game.setDealerScore(request.getDealerScore());
        game.setDeck(request.getDeck());
        gameService.playerHit(game);
        GameDTO gamedto = new GameDTO(game);
        return ResponseEntity.ok(gamedto);
    }

    @PostMapping("/stand")
    public ResponseEntity<GameDTO> playerStand(@RequestBody GameDTO request) {
        Game game = gameService.findById(request.getGameId());
        game.setPlayerHand(request.getPlayerHand());
        game.setDealerHand(request.getDealerHand());
        game.setPlayerScore(request.getPlayerScore());
        game.setDealerScore(request.getDealerScore());
        game.setDeck(request.getDeck());
        gameService.playerStand(game);
        GameDTO gamedto = new GameDTO(game);
        return ResponseEntity.ok(gamedto);
    }

    @PostMapping("/surrender")
    public ResponseEntity<GameDTO> playerSurrender(@RequestBody GameDTO request) {
        Game game = gameService.findById(request.getGameId());
        game.setPlayerHand(request.getPlayerHand());
        game.setDealerHand(request.getDealerHand());
        game.setPlayerScore(request.getPlayerScore());
        game.setDealerScore(request.getDealerScore());
        game.setDeck(request.getDeck());
        gameService.playerSurrender(game);
        GameDTO gamedto = new GameDTO(game);
        return ResponseEntity.ok(gamedto);
    }

    @PostMapping("/double")
    public ResponseEntity<GameDTO> playerDouble(@RequestBody GameDTO request) {
        Game game = gameService.findById(request.getGameId());
        game.setPlayerHand(request.getPlayerHand());
        game.setDealerHand(request.getDealerHand());
        game.setPlayerScore(request.getPlayerScore());
        game.setDealerScore(request.getDealerScore());
        game.setDeck(request.getDeck());
        gameService.playerDouble(game);
        GameDTO gamedto = new GameDTO(game);
        return ResponseEntity.ok(gamedto);
    }

}