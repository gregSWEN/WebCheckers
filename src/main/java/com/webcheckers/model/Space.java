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

    public Space(int index, SpaceColor color, Piece piece){
        this.color = color;
        this.cellIdx = index;
        this.piece = piece;
    }

    public int getCellIdx() {
        return cellIdx;
    }

    public boolean isValid(){
        if((color == SpaceColor.GREY) && (piece == null)){
            return true;
        }
        return false;
    }

    public Piece getPiece(){
        return piece;
    }
}
