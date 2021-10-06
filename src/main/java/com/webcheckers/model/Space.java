package com.webcheckers.model;

public class Space {
    public enum SpaceColor{
        WHITE,
        BLACK,
        GREY;
    }


    private int cellIdx;
    private SpaceColor color;
    private Piece piece;

    /*
    initializes the space object
     */
    public Space(int index, SpaceColor color, Piece piece){
        this.color = color;
        this.cellIdx = index;
        this.piece = piece;
    }

    /*
    returns the cell index
     */
    public int getCellIdx() {
        return cellIdx;
    }

    /*
    this determines if the space is valid for a piece to be placed
     */
    public boolean isValid(){
        if((color == SpaceColor.GREY) && (piece == null)){
            return true;
        }
        return false;
    }

    /*
    return the piece
     */
    public Piece getPiece(){
        return piece;
    }
}
