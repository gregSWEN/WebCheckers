package com.webcheckers.model;

import java.util.List;
import java.util.Stack;

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
    /** list of moves a player did */
    private Stack<Move> moves;
    /** boolean if player made a move that turn */
    private boolean madeMove;
    private Move multiCapture;

    public Player(String name) {
        this.name = name;
        this.moves = new Stack<>();
        this.madeMove = false;
        this.multiCapture = null;
    }
    public String getName() { return name; }
    public GameModel getGame(){return game;}
    public boolean getMadeMove(){return madeMove;}

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

    /**
     * add move to moves (a stack)
     * @param move valid move
     * @return void
     */
    public void addMove(Move move){
        this.moves.add((move));
    }

    /**
     * add move to moves (a stack)
     * @param
     * @return lastest Move from the player
     */
    public Move peekMove(){
        return this.moves.peek();
    }
    /**
     * change if the player has made a turn or undo a move
     * @param turn boolean
     */
    public void madeTurn(boolean turn){
        this.madeMove = turn;
    }
    public Move popMove(){
        return this.moves.pop();
    }

    @Override
    public int hashCode() { return name.hashCode(); }

    public void startGame(GameModel game){
        this.game = game;
    }

    public void endGame(){this.game = null;}

    public void addMultiMove(Move move){this.multiCapture = move;}

    public Move getMultiCapture(){return multiCapture;}
}
