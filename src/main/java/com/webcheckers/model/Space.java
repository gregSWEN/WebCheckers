package com.webcheckers.model;

public class Space {
    private int cellIdx;

    public Space(int index){
        this.cellIdx = index;
    }

    public int getCellIdx() {
        return cellIdx;
    }

    public boolean isValid(){
        return false;
    }

    public Piece getPiece(){
        return null;
    }
}
