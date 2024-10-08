package com.backcasino.services;

import com.backcasino.DAO.PlayerDAO;
import com.backcasino.models.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PlayerService {

    @Autowired
    private PlayerDAO playerDAO;

    public void createPlayer(String username, String passwordHash, String email) {
        Player player = new Player();
        player.setUsername(username);
        player.setPasswordHash(passwordHash);
        player.setEmail(email);
        player.setTokenBalance(100);  // Solde initial
        playerDAO.save(player);
        System.out.println("Player created with ID: " + player.getId());
    }

    public void findPlayerById(int id) {
        Optional<Player> playerOpt = playerDAO.findById(id);
        if (playerOpt.isPresent()) {
            Player player = playerOpt.get();
            System.out.println("Player found: " + player.getUsername());
        } else {
            System.out.println("Player not found.");
        }
    }

    public void updateTokenBalance(int playerId, int amount) {
        Optional<Player> playerOpt = playerDAO.findById(playerId);
        if (playerOpt.isPresent()) {
            Player player = playerOpt.get();
            player.adjustTokenBalance(amount);
            playerDAO.save(player);
            System.out.println("Player's token balance updated to: " + player.getTokenBalance());
        } else {
            System.out.println("Player not found.");
        }
    }

    public void deletePlayerById(int id) {
        playerDAO.deleteById(id);
        System.out.println("Player with ID " + id + " deleted.");
    }
}
