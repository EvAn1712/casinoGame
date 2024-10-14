package com.backcasino.models;


import lombok.Getter;
import lombok.Setter;

@Getter
public enum Card {
    TWO_OF_HEARTS("HEARTS", "2", 2),
    THREE_OF_HEARTS("HEARTS", "3", 3),
    FOUR_OF_HEARTS("HEARTS", "4", 4),
    FIVE_OF_HEARTS("HEARTS", "5", 5),
    SIX_OF_HEARTS("HEARTS", "6", 6),
    SEVEN_OF_HEARTS("HEARTS", "7", 7),
    EIGHT_OF_HEARTS("HEARTS", "8", 8),
    NINE_OF_HEARTS("HEARTS", "9", 9),
    TEN_OF_HEARTS("HEARTS", "10", 10),
    JACK_OF_HEARTS("HEARTS", "JACK", 10),
    QUEEN_OF_HEARTS("HEARTS", "QUEEN", 10),
    KING_OF_HEARTS("HEARTS", "KING", 10),
    ACE_OF_HEARTS("HEARTS", "ACE", 11),

    TWO_OF_DIAMONDS("DIAMONDS", "2", 2),
    THREE_OF_DIAMONDS("DIAMONDS", "3", 3),
    FOUR_OF_DIAMONDS("DIAMONDS", "4", 4),
    FIVE_OF_DIAMONDS("DIAMONDS", "5", 5),
    SIX_OF_DIAMONDS("DIAMONDS", "6", 6),
    SEVEN_OF_DIAMONDS("DIAMONDS", "7", 7),
    EIGHT_OF_DIAMONDS("DIAMONDS", "8", 8),
    NINE_OF_DIAMONDS("DIAMONDS", "9", 9),
    TEN_OF_DIAMONDS("DIAMONDS", "10", 10),
    JACK_OF_DIAMONDS("DIAMONDS", "JACK", 10),
    QUEEN_OF_DIAMONDS("DIAMONDS", "QUEEN", 10),
    KING_OF_DIAMONDS("DIAMONDS", "KING", 10),
    ACE_OF_DIAMONDS("DIAMONDS", "ACE", 11),

    TWO_OF_CLUBS("CLUBS", "2", 2),
    THREE_OF_CLUBS("CLUBS", "3", 3),
    FOUR_OF_CLUBS("CLUBS", "4", 4),
    FIVE_OF_CLUBS("CLUBS", "5", 5),
    SIX_OF_CLUBS("CLUBS", "6", 6),
    SEVEN_OF_CLUBS("CLUBS", "7", 7),
    EIGHT_OF_CLUBS("CLUBS", "8", 8),
    NINE_OF_CLUBS("CLUBS", "9", 9),
    TEN_OF_CLUBS("CLUBS", "10", 10),
    JACK_OF_CLUBS("CLUBS", "JACK", 10),
    QUEEN_OF_CLUBS("CLUBS", "QUEEN", 10),
    KING_OF_CLUBS("CLUBS", "KING", 10),
    ACE_OF_CLUBS("CLUBS", "ACE", 11),

    TWO_OF_SPADES("SPADES", "2", 2),
    THREE_OF_SPADES("SPADES", "3", 3),
    FOUR_OF_SPADES("SPADES", "4", 4),
    FIVE_OF_SPADES("SPADES", "5", 5),
    SIX_OF_SPADES("SPADES", "6", 6),
    SEVEN_OF_SPADES("SPADES", "7", 7),
    EIGHT_OF_SPADES("SPADES", "8", 8),
    NINE_OF_SPADES("SPADES", "9", 9),
    TEN_OF_SPADES("SPADES", "10", 10),
    JACK_OF_SPADES("SPADES", "JACK", 10),
    QUEEN_OF_SPADES("SPADES", "QUEEN", 10),
    KING_OF_SPADES("SPADES", "KING", 10),
    ACE_OF_SPADES("SPADES", "ACE", 11);

    // Propriétés de l'enum
    private final String suit;
    private final String rank;
    private final int value;

    Card(String suit, String rank, int value) {
        this.suit = suit;
        this.rank = rank;
        this.value = value;
    }

}