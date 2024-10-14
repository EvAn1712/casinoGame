package com.backcasino.controller;

import com.backcasino.services.BlackjackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/blackjack")
public class BlackjackController {

    @Autowired
    private BlackjackService blackjackService;

    @PostMapping("/start")
    public void startNewGame() {
        blackjackService.startNewGame();
    }

    @PostMapping("/hit")
    public void playerHit() {
        blackjackService.playerHit();
    }

    @PostMapping("/stand")
    public void playerStand() {
        blackjackService.playerStand();
    }

    @GetMapping("/check-winner")
    public void checkWinner() {
        blackjackService.checkWinner();
    }
}
