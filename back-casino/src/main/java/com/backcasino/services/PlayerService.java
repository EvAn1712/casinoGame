package com.backcasino.services;

import com.backcasino.DAO.PlayerDAO;
import com.backcasino.models.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {

    @Autowired
    private PlayerDAO playerDAO;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public void createPlayer(String username, String passwordHash, String email) {
        Player player = new Player();
        player.setUsername(username);
        player.setPasswordHash(passwordHash);
        player.setEmail(email);
        player.setTokenBalance(1000);
        playerDAO.save(player);
    }


    public Player findByUsername(String username) {
        return playerDAO.findByUsername(username);
    }

}
