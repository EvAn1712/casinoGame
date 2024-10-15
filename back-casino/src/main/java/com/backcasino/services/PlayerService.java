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

    public void createPlayer(String username, String password, String email) {
        // Encode le mot de passe avant de le sauvegarder
        String encodedPassword = bCryptPasswordEncoder.encode(password);
        Player player = new Player(0, username, encodedPassword, email, 1000);  // Création du joueur avec un solde de jetons initial à 0
        playerDAO.save(player);
    }

    public Player findByUsername(String username) {
        return playerDAO.findByUsername(username);
    }

}
