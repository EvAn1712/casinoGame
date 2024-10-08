package com.backcasino.services;

import com.backcasino.DAO.PlayerDAO;
import com.backcasino.models.Player;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

public class PlayerService {
    private final PlayerDAO playerDAO;

    public PlayerService(Connection connection) {
        this.playerDAO = new PlayerDAO(connection);
    }

    // Créer un joueur
    public void registerPlayer(Player player) throws SQLException {
        playerDAO.createPlayer(player);
    }

    // Authentifier un joueur
    public Optional<Player> authenticate(String username, String passwordHash) throws SQLException {
        return playerDAO.authenticate(username, passwordHash);
    }

    // Trouver un joueur par son nom d'utilisateur
    public Optional<Player> findByUsername(String username) throws SQLException {
        return playerDAO.findByUsername(username);
    }

    // Mettre à jour le mot de passe
    public void updatePassword(Long playerId, String newPasswordHash) throws SQLException {
        playerDAO.updatePassword(playerId, newPasswordHash);
    }
}
