package com.webcheckers.model;

public class Space {
    public enum SpaceColor{
        WHITE,
        BLACK,
        GREY
    }


    private int cellIdx;
    private SpaceColor color;
    private Piece piece;

    /**
     * initializes the Space object
     * @param index
     * @param color
     * @param piece
     */
    public Space(int index, SpaceColor color, Piece piece){
        this.color = color;
        this.cellIdx = index;
        this.piece = piece;
    }

    /**
     *
     * @return
     * the cell index
     */
    public int getCellIdx() {
        return cellIdx;
    }

    public SpaceColor getColor() {return color;}

    /**
     * this determines if the space if valid
     * for a piece to be placed
     * @return
     * true or false
     */
    public boolean isValid(){
        return (color == SpaceColor.BLACK) && (piece == null);
    }

    /**
     *
     * @return
     * the piece
     */
    public Piece getPiece(){
        return piece;
    }

    public boolean isPieceRed() { return piece.getColor() == Piece.Color.RED; }

    public boolean isPieceWhite() { return piece.getColor() == Piece.Color.WHITE; }

    public boolean isPieceKing() { return piece.getType() == Piece.Type.KING; }

    public void setPiece (Piece piece) { this.piece = piece; }

    public void setNull() {this.piece =null;}

    @Override
    public String toString(){
        return "Color: "+this.color + "  Piece: "+this.piece;
    }
}
