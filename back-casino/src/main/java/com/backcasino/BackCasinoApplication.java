package com.backcasino;

import com.backcasino.services.PlayerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BackCasinoApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackCasinoApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(PlayerService playerService) {
        return (args) -> {
            // Créer un joueur
            playerService.createPlayer("john_doe", "hashedPassword", "john@example.com");

            // Rechercher un joueur par ID
            playerService.findPlayerById(1);

            // Mettre à jour le solde de jetons
            playerService.updateTokenBalance(1, 50);

            // Supprimer un joueur
            playerService.deletePlayerById(1);
        };
    }
}
