package com.webcheckers.model;

public class Piece {
    public enum Type{
        SINGLE,
        KING;
    }
    public enum Color{
        RED,
        WHITE;
    }

    private Type type;
    private Color color;

    /*
    initializes the piece object
     */
    public Piece(Type type, Color color){
        this.type = type;
        this.color = color;
    }

    /*
    returns the Color of the piece
     */
    public Color getColor() {
        return color;
    }

    /*
    returns the type of piece
     */
    public Type getType() {
        return type;
    }
}
