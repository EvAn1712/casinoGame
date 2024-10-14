package com.backcasino.services;

import com.backcasino.DAO.BlackjackDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlackjackService {
    @Autowired
    private BlackjackDAO blackjackDAO;

    public void startNewGame() {
        // Initialize a new game
    }

    public void playerHit() {
        // Logic for player hit
    }

    public void playerStand() {
        // Logic for player stand
    }

    public void dealerTurn() {
        // Logic for dealer's turn
    }

    public void checkWinner() {
        // Logic to check the winner
    }

}
