package com.backcasino;

import com.backcasino.models.Game;
import com.backcasino.models.Player;
import com.backcasino.models.Bet;
import com.backcasino.services.GameService;
import com.backcasino.services.PlayerService;
import com.backcasino.services.BetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Scanner;

@SpringBootApplication
public class BackCasinoApplication {

    @Autowired
    private GameService gameService;

    @Autowired
    private PlayerService playerService;

    @Autowired
    private BetService betService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(BackCasinoApplication.class, args);
    }

}
