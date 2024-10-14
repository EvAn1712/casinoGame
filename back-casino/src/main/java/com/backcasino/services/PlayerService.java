package com.backcasino.services;

import com.backcasino.DAO.PlayerDAO;
import com.backcasino.models.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PlayerService {

    @Autowired
    private static PlayerDAO playerDAO;

    @Autowired
    private static BCryptPasswordEncoder bCryptPasswordEncoder;

    public static void registerUser(Player player) {
        //player.setPasswordHash(bCryptPasswordEncoder.encode(player.getPasswordHash()));
        playerDAO.save(player);
    }

    public void createPlayer(String username, String password, String email) {
        playerDAO.save(new Player(1, username, password, email, 0));
    }

    public Player findByUsername(String username) {
        return playerDAO.findByUsername(username);
    }
}
