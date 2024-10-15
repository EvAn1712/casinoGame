package com.backcasino.services;

import com.backcasino.DAO.BetDAO;
import com.backcasino.DAO.PlayerDAO;
import com.backcasino.models.Bet;
import com.backcasino.models.Game;
import com.backcasino.models.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BetService {

    @Autowired
    private BetDAO betDAO;

    @Autowired
    private PlayerDAO playerDAO;

    public Bet placeBet(int amount, Player player, Game game) {

        //playerDAO.save(player);

        Bet bet = new Bet();
        bet.setAmount(amount);
        bet.setPlayer(player);
        bet.setGame(game);
        betDAO.save(bet);
        return bet;
    }

    //@Transactional
    /*public void resolveBet(Bet bet, boolean win) {
        Player player = bet.getPlayer();
        if (win) {
            player.setTokenBalance(player.getTokenBalance() + bet.getAmount() * 2);
            bet.setOutcome(bet.getAmount());
        } else {
            bet.setOutcome(-bet.getAmount());
        }
        playerDAO.save(player);
        betDAO.save(bet);
    }*/

    public void resolveBet(Bet bet, boolean win) {
        int player = bet.getPlayer().getId();
        Player players = playerDAO.findById(player).orElseThrow();
        System.out.println(players);
        if (win) {
            players.setTokenBalance(players.getTokenBalance() + bet.getAmount() * 2);
            bet.setOutcome(bet.getAmount());
        } else {
            bet.setOutcome(-bet.getAmount());
        }
        playerDAO.save(players);
        betDAO.save(bet);
    }


    public void deleteBet(int id) {
        betDAO.deleteById(id);
    }

    public void deleteAllBets() {
        betDAO.deleteAll();
    }

    public Bet getBet(int id) {
        return betDAO.findById(id).orElseThrow();
    }
}
