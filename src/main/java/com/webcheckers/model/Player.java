package com.webcheckers.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
    private boolean multiCapture;
    private Set<GameModel> games;

    public Player(String name) {
        this.name = name;
        this.moves = new Stack<>();
        this.madeMove = false;
        this.multiCapture = false;
        this.games = new HashSet<>();
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

    /**
     * check if the game is already happening with the same opponent
     * @param enemy Enemy player
     */
    public boolean checkIfPlayerInGame(Player enemy){
        for (GameModel s : games) {
            boolean alreadyGame = s.checkTwoPlayers(this, enemy);
            if(alreadyGame){    //if two players already in a game return true
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() { return name.hashCode(); }

    public void startGame(GameModel game){
        this.game = game;
    }

    public void endGame(){this.game = null;}

    public void setMultiCapture(boolean move){this.multiCapture = move;}

    public boolean getMultiCapture(){return multiCapture;}

    public void addGameToPlayer(GameModel game){games.add(game);}

    public Set<GameModel> getPlayerGames(){return games;}
}