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
    private PlayerDAO playerDAO;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public void registerUser(Player player) {
        player.setPasswordHash(bCryptPasswordEncoder.encode(player.getPasswordHash()));
        playerDAO.save(player);
    }

    public Player findByUsername(String username) {
        return playerDAO.findByUsername(username);
    }
}
