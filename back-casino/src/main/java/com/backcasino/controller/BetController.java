//package com.backcasino.controller;
//
//import com.backcasino.models.Player;
//import com.backcasino.services.BetService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/bet")
//public class BetController {
//
//    @Autowired
//    private BetService betService;
//
//    @PostMapping("/place_roulette_bet")
//    public void placeRouletteBet(@RequestParam int amount, @RequestParam int[] numbers, @RequestParam String betType) {
//        Player player = new Player(); // Retrieve the player instance
//        RouletteGame game = new RouletteGame(); // Retrieve the current game instance
//
//        betService.placeRouletteBet(amount, player, game, betType, numbers);
//    }
//}
