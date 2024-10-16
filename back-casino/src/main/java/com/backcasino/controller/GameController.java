package com.backcasino.controller;

import com.backcasino.DTO.GameCreationRequest;
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
        Player player = game.getPlayer();
        betService.placeBet(request.getBetAmount(), player, game);
        GameDTO gameDTO = new GameDTO(game);
        return ResponseEntity.ok(gameDTO);
    }

    @PostMapping("/hit")
    public ResponseEntity<GameDTO> playerHit(@RequestParam Integer gameId, @RequestParam Integer betId) {
        Game game = gameService.findById(gameId);
        Bet bet = betService.getBet(betId);
        gameService.playerHit(game, bet);
        return ResponseEntity.ok(new GameDTO(game));
    }

    @PostMapping("/stand")
    public ResponseEntity<GameDTO> playerStand(@RequestParam Integer gameId, @RequestParam Integer betId) {
        Game game = gameService.findById(gameId);
        Bet bet = betService.getBet(betId);
        gameService.playerStand(game, bet);
        return ResponseEntity.ok(new GameDTO(game));
    }

    @PostMapping("/surrender")
    public ResponseEntity<GameDTO> playerSurrender(@RequestParam Integer gameId) {
        Game game = gameService.findById(gameId);
        gameService.playerSurrender(game);
        return ResponseEntity.ok(new GameDTO(game));
    }

    @PostMapping("/double")
    public ResponseEntity<GameDTO> playerDouble(@RequestParam Integer gameId) {
        Game game = gameService.findById(gameId);
        gameService.playerDouble(game);
        return ResponseEntity.ok(new GameDTO(game));
    }

    @PostMapping("/end")
    public ResponseEntity<Void> endGame(@RequestParam Integer gameId) {
        gameService.endGame(gameId);
        return ResponseEntity.ok().build();
    }
}
