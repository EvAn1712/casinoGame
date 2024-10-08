package com.backcasino;

import com.backcasino.DAO.PlayerDAO;
import com.backcasino.models.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.Optional;

@Component
public class ApplicationRunner implements CommandLineRunner {

    @Autowired
    private PlayerDAO playerDAO;

    @Override
    public void run(String... args) throws Exception {
        // Création d'un nouveau joueur
        Player newPlayer = new Player();
        newPlayer.setUsername("john_doe");
        newPlayer.setPasswordHash("hashed_password");
        newPlayer.setEmail("john@example.com");
        newPlayer.setTokenBalance(100);

        try {
            playerDAO.createPlayer(newPlayer);
            System.out.println("Player created with ID: " + newPlayer.getId());

            // Recherche du joueur par nom d'utilisateur
            Optional<Player> foundPlayer = playerDAO.findByUsername("john_doe");
            foundPlayer.ifPresent(player -> {
                System.out.println("Found player: " + player.getUsername() + " with token balance: " + player.getTokenBalance());
            });

            // Authentification
            Optional<Player> authenticatedPlayer = playerDAO.authenticate("john_doe", "hashed_password");
            authenticatedPlayer.ifPresent(player -> {
                System.out.println("Authenticated player: " + player.getUsername());
            });

            // Mise à jour du mot de passe
            playerDAO.updatePassword(newPlayer.getId(), "new_hashed_password");
            System.out.println("Password updated for player ID: " + newPlayer.getId());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

