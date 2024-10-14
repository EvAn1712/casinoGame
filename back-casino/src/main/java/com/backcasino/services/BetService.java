//package com.backcasino.services;
//
//import com.backcasino.DAO.BetDAO;
//import com.backcasino.models.Bet;
//import com.backcasino.models.Player;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class BetService {
//
//    @Autowired
//    private BetDAO betDAO;
//
//    public void placeRouletteBet(int amount, Player player, RouletteGame game, String betType, int[] numbers) {
//        Bet bet = new Bet(amount, player, game);
//
//        switch (betType.toLowerCase()) {
//            case "plein":
//                bet.setPayout(35 * amount);
//                break;
//            case "cheval":
//                if (numbers.length == 2) {
//                    bet.setPayout(17 * amount);
//                }
//                break;
//            case "transversale":
//                if (numbers.length == 3) {
//                    bet.setPayout(11 * amount);
//                }
//                break;
//            case "carré":
//                if (numbers.length == 4) {
//                    bet.setPayout(8 * amount);
//                }
//                break;
//            case "sixain":
//                if (numbers.length == 6) {
//                    bet.setPayout(5 * amount);
//                }
//                break;
//            case "chances simples":
//                bet.setPayout(amount);
//                break;
//            case "douzaine", "colonne":
//                bet.setPayout(2 * amount);
//                break;
//            case "douzaines à cheval":
//                if (numbers.length == 24) {
//                    bet.setPayout((int) (0.5 * amount));
//                }
//                break;
//            default:
//                throw new IllegalArgumentException("Invalid bet type");
//        }
//
//        player.setTokenBalance(player.getTokenBalance() - amount);
//        betDAO.save(bet);
//    }
//}