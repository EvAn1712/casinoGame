package com.backcasino.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "games")
public class Game {

    // Getters et setters
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Définit le joueur associé
    // Récupère le joueur associé
    @ManyToOne
    @JoinColumn(name = "player_id") // Indique que player_id est la clé étrangère
    private Player player; // Référence à l'entité Player

    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private double result;
    private String gameType;
    private int winningNumber;

}
