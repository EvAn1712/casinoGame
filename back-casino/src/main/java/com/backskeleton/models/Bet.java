package main.java.com.backskeleton.models;

public class Bet {
    private int amount;
    private int playerId;
    private int gameId;

    public Bet(int amount, int playerId, int gameId) {
        this.amount = amount;
        this.playerId = playerId;
        this.gameId = gameId;
    }

    public int getAmount() {
        return amount;
    }

    public int getPlayerId() {
        return playerId;
    }

    public int getGameId() {
        return gameId;
    }
}
