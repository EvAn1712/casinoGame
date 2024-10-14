package com.backcasino.controller;

import com.backcasino.services.RouletteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/roulette")
public class RouletteController {

    @Autowired
    private RouletteService rouletteService;

    @PostMapping("/bet")
    public void placeBet(@RequestParam int betNumber, @RequestParam String betType) {
        rouletteService.placeBet(betNumber, betType);
    }

    @PostMapping("/spin")
    public void spinWheel() {
        rouletteService.spinWheel();
    }

    @GetMapping("/result")
    public void checkResult() {
        rouletteService.checkResult();
    }
}