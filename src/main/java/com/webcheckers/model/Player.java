package com.webcheckers.model;

/**
 * This class represents an Entity object
 * for a player to play checkers
 * @author Michael Taylor
 */
public class Player {
    /** name of player */
    private final String name;
    /** the current game the player is in */
    private GameModel game;

    public Player(String name) { this.name = name; }
    public String getName() { return name; }
    public GameModel getGame(){return game;}

    /**
     * Two players are equal if their names are
     * the same
     * @param obj other Player (or Object)
     * @return if the two Objects (Players) are
     * the same
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof Player)) return false;
        final Player that = (Player) obj;
        return this.name.equals(that.name);
    }

    @Override
    public int hashCode() { return name.hashCode(); }

    public void startGame(GameModel game){
        this.game = game;
    }
}
