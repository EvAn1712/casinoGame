package com.backcasino.controller;

import com.backcasino.DTO.PlayerLoginDTO;
import com.backcasino.DTO.PlayerRegistrationDTO;
import com.backcasino.models.Player;
import com.backcasino.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RestController
@CrossOrigin
@RequestMapping("/player")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public void registerPlayer(@RequestBody PlayerRegistrationDTO registrationData) {
        String encodedPassword = passwordEncoder.encode(registrationData.getPassword());
        playerService.createPlayer(registrationData.getUsername(), encodedPassword, registrationData.getEmail());
    }

    @PostMapping("/login")
    public boolean loginPlayer(@RequestBody PlayerLoginDTO loginData) {
        Player player = playerService.findByUsername(loginData.getUsername());

        if (player == null) {
            System.out.println("Utilisateur non trouvé");
            return false;
        }

        boolean passwordMatch = passwordEncoder.matches(loginData.getPassword(), player.getPasswordHash());
        if (!passwordMatch) {
            System.out.println("Le mot de passe ne correspond pas");
        }

        return passwordMatch;
    }
}
