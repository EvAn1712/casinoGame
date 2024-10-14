package com.backcasino.controller;

import com.backcasino.models.Player;
import com.backcasino.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RestController
@RequestMapping("/player")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @PostMapping("/register")
    public void registerPlayer(@RequestBody Player player) {
        playerService.registerUser(player);
    }

    @PostMapping("/login")
    public boolean loginPlayer(@RequestParam String username, @RequestParam String password) {
        Player player = playerService.findByUsername(username);
        return player != null && new BCryptPasswordEncoder().matches(password, player.getPasswordHash());
    }
}
