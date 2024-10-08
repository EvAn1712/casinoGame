package com.backcasino.DAO;

import com.backcasino.models.Game;
import com.backcasino.models.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class GameDAO {

    private final Connection connection;

    public GameDAO(Connection connection) {
        this.connection = connection;
    }

    // Créer un nouveau jeu
    public void createGame(Game game) throws SQLException {
        String sql = "INSERT INTO games (player_id, game_type, start_time, result, winning_number) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, game.getPlayer_id());
            stmt.setString(2, game.getGameType());
            stmt.setTimestamp(3, Timestamp.valueOf(game.getStartTime()));
            stmt.setDouble(4, game.getResult());
            stmt.setInt(5, game.getWinningNumber());
            stmt.executeUpdate();

            // Récupérer l'ID du jeu créé
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    game.setId(generatedKeys.getInt(1));
                }
            }
        }
    }

    // Récupérer tous les jeux d'un joueur
    public List<Game> findGamesByPlayer(int playerId) throws SQLException {
        String sql = "SELECT * FROM games WHERE player_id = ?";
        List<Game> games = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, playerId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Game game = new Game();
                    game.setId(rs.getInt("id"));
                    game.setPlayer_id(rs.getInt("player_id"));
                    game.setStartTime(rs.getTimestamp("start_time").toLocalDateTime());
                    game.setEndTime(rs.getTimestamp("end_time") != null ? rs.getTimestamp("end_time").toLocalDateTime() : null);
                    game.setResult(rs.getDouble("result"));
                    game.setWinningNumber(rs.getInt("winning_number"));
                    games.add(game);
                }
            }
        }
        return games;
    }
}