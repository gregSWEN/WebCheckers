package com.webcheckers.model;

import com.webcheckers.appl.GameManager;
import com.webcheckers.util.Message;

public class GameModel {
    private final Player red_player;
    private final Player white_player;
    private BoardView board;
    private Piece.Color currentColor;
    private boolean isRedTurn;
    private boolean isWhiteTurn;

    //initiates the class
    public GameModel(Player red_player, Player white_player){
        this.red_player = red_player;
        this.white_player = white_player;
        this.isRedTurn = true;
        this.isWhiteTurn = false;

        //put both players into this game, this will also not allow other
        //players to start a game with them
        //also makes the board using gameManger and sets first move to red
        this.red_player.startGame(this);
        this.white_player.startGame(this);
        this.board = GameManager.make_board();
        this.currentColor = Piece.Color.RED;
    }

    /*
    GETTERS
     */
    public Player getRedPlayer(){return red_player;}
    public Player getWhitePlayer(){return white_player;}
    public Piece.Color getActiveColor(){return currentColor;}
    public BoardView getBoard(){return board;}

    public void updateBoard(BoardView newBoard) {this.board = newBoard;}

    public Message checkTurn(Player current){
        if(current.equals(red_player) && isRedTurn){
            return Message.info("true");
        }
        else if(current.equals(white_player) && isWhiteTurn){
            return Message.info("true");
        }
        else{
            return Message.error("false");
        }
    }


}
