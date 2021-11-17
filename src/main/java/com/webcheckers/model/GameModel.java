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
    private String how_game_ended;
    private boolean game_end;
    private Player loser;
    private Player winner;
    private int id;

    //initiates the class
    public GameModel(Player red_player, Player white_player, int id){
        this.red_player = red_player;
        this.white_player = white_player;
        this.isRedTurn = true;
        this.isWhiteTurn = false;
        this.id = id;

        //put both players into this game, this will also not allow other
        //players to start a game with them
        //also makes the board using gameManger and sets first move to red
        this.red_player.startGame(this);
        this.white_player.startGame(this);
        this.board = GameManager.make_board();
        this.currentColor = Piece.Color.RED;
        this.how_game_ended = "resigned";
        this.game_end = false;
        this.loser = null;
    }

    /*
    GETTERS
     */
    public Player getRedPlayer(){return red_player;}
    public Player getWhitePlayer(){return white_player;}
    public Piece.Color getActiveColor(){return currentColor;}
    public BoardView getBoard(){return board;}
    public boolean getGameStatus(){return game_end;}
    public String get_how_game_ended(){return how_game_ended;}
    public Player get_loser(){return loser;}
    public Player get_winner(){return winner;}

    public void setActiveColor(Piece.Color color){
        this.currentColor = color;
    }

    /**
     * set the game to end
     * @param loser player that lost
     */
    public void resign (Player loser){
        this.game_end = true;
        this.loser = loser;
        if(loser == this.white_player){
            this.winner = red_player;
        }else{
            this.winner = white_player;
        }
        red_player.popGame(this);
        white_player.popGame(this);
    }

    public boolean checkTwoPlayers(Player p1, Player p2){
        if(red_player == p1 && white_player == p2){
            return true;
        }else if(white_player == p1 && red_player == p2){
            return true;
        }else {
            return false;
        }

    }

    public int getId(){return id;}

    @Override
    public String toString() {
        return this.red_player.getName() + " VS " + this.white_player.getName();
    }
    /**
     * set the game to end
     * @param loser player that lost
     */
    public void finished_game (Player loser){
        this.game_end = true;
        this.loser = loser;
        if(loser == this.white_player){
            this.winner = red_player;
        }else{
            this.winner = white_player;
        }
        this.how_game_ended = "all pieces captured";
    }

    /**
     * check if the game is over by seeing if they're pieces left
     */
    public Player checkIfOver(){
        boolean red = false;
        boolean white = false;
        for (int row = 0; row < 8; row++) {
            for (int cell = 0; cell < 8; cell++) {
                try{
                    Space space = board.getSpaceAt(row, cell);
                    if(space.getPiece().getColor() == Piece.Color.RED){
                        red = true;
                    }else if(space.getPiece().getColor() == Piece.Color.WHITE){
                        white = true;
                    }

                    if(red && white){
                        break;
                    }
                }catch(NullPointerException e){
                    //pass
                }
            }
        }
        if(red == false){
            return red_player;
        }
        else if(white == false){
            return white_player;
        }else{
            return null;
        }
    }


}
