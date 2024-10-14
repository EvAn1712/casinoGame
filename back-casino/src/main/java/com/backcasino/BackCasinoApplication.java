package com.backcasino;

import com.backcasino.models.Game;
import com.backcasino.models.Player;
import com.backcasino.services.GameService;
import com.backcasino.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Scanner;

@SpringBootApplication
public class BackCasinoApplication {

    @Autowired
    private GameService gameService;

    @Autowired
    private PlayerService playerService;

    public static void main(String[] args) {
        SpringApplication.run(BackCasinoApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo() {
        return args -> {
            // Créer un joueur pour la démonstration

            playerService.createPlayer("test", "test", "test");
            Player player = playerService.findByUsername("test");
            System.out.println("Joueur créé avec succès : " + player.getUsername());

            // Créer une nouvelle partie pour le joueur créé
            Game game = gameService.createGame(player.getId()); // Assurez-vous que l'ID est correct
            gameService.startGame(game);

            System.out.println("Jeu démarré pour le joueur " + game.getPlayer().getUsername());
            afficherEtatJeu(game);

            Scanner scanner = new Scanner(System.in);
            boolean finDuJeu = false;

            // Boucle de jeu pour que le joueur prenne des décisions
            while (!finDuJeu) {
                System.out.println("Que voulez-vous faire ? (1 = Tirer, 2 = Rester, 3 = Doubler, 4 = Abandonner)");
                int choix = scanner.nextInt();

                switch (choix) {
                    case 1:
                        // Tirer
                        gameService.playerHit(game);
                        System.out.println("Vous avez tiré une carte.");
                        afficherEtatJeu(game);
                        break;
                    case 2:
                        // Rester
                        gameService.playerStand(game);
                        System.out.println("Vous avez choisi de rester.");
                        afficherEtatJeu(game);
                        finDuJeu = true;
                        break;
                    case 3:
                        // Doubler
                        gameService.playerDouble(game);
                        System.out.println("Vous avez doublé.");
                        afficherEtatJeu(game);
                        finDuJeu = true;
                        break;
                    case 4:
                        // Abandonner
                        gameService.playerSurrender(game);
                        System.out.println("Vous avez abandonné.");
                        finDuJeu = true;
                        break;
                    default:
                        System.out.println("Choix non valide. Veuillez entrer un numéro entre 1 et 4.");
                }

                if (game.isGameOver()) {
                    System.out.println("La partie est terminée.");
                    finDuJeu = true;
                }
            }

            gameService.endGame(game.getId());
            System.out.println("Partie terminée pour le joueur " + game.getPlayer().getUsername());
        };
    }

    private void afficherEtatJeu(Game game) {
        System.out.println("Main du joueur : " + game.getPlayerHand());
        System.out.println("Score du joueur : " + game.getPlayerScore());
        System.out.println("Main du croupier : " + game.getDealerHand());
        System.out.println("Score du croupier : " + game.getDealerScore());
    }
}
