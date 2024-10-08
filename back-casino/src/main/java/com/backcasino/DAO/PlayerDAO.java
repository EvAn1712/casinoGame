package com.backcasino.DAO;

import com.backcasino.models.Player;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.Optional;

@Repository
public class PlayerDAO {

    private final Connection connection;

    public PlayerDAO(Connection connection) {
        this.connection = connection;
    }

    // Créer un joueur
    public void createPlayer(Player player) throws SQLException {
        String sql = "INSERT INTO players (username, password_hash, email, token_balance) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, player.getUsername());
            stmt.setString(2, player.getPasswordHash());
            stmt.setString(3, player.getEmail());
            stmt.setInt(4, player.getTokenBalance());
            stmt.executeUpdate();

            // Récupérer l'ID généré et le définir dans le Player
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    player.setId(generatedKeys.getInt(1));
                }
            }
        }
    }

    // Trouver un joueur par son nom d'utilisateur
    public Optional<Player> findByUsername(String username) throws SQLException {
        String sql = "SELECT * FROM players WHERE username = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Player player = new Player();
                    player.setId(rs.getInt("id"));
                    player.setUsername(rs.getString("username"));
                    player.setPasswordHash(rs.getString("password_hash"));
                    player.setEmail(rs.getString("email"));
                    player.setTokenBalance(rs.getInt("token_balance"));
                    return Optional.of(player);
                }
            }
        }
        return Optional.empty();
    }

    // Mettre à jour le mot de passe du joueur
    public void updatePassword(Long playerId, String newPasswordHash) throws SQLException {
        String sql = "UPDATE players SET password_hash = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, newPasswordHash);
            stmt.setLong(2, playerId);
            stmt.executeUpdate();
        }
    }

    // Authentification
    public Optional<Player> authenticate(String username, String passwordHash) throws SQLException {
        String sql = "SELECT * FROM players WHERE username = ? AND password_hash = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, passwordHash);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Player player = new Player();
                    player.setId(rs.getInt("id"));
                    player.setUsername(rs.getString("username"));
                    player.setPasswordHash(rs.getString("password_hash"));
                    player.setEmail(rs.getString("email"));
                    player.setTokenBalance(rs.getInt("token_balance"));
                    return Optional.of(player);
                }
            }
        }
        return Optional.empty();
    }
}
