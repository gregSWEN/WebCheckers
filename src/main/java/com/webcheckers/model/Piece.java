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

    /**
     * initializes the Piece object
     * @param type
     * @param color
     */
    public Piece(Type type, Color color){
        this.type = type;
        this.color = color;
    }

    /**
     *
     * @return
     * the color
     */
    public Color getColor() {
        return color;
    }

    /**
     *
     * @return
     * the type
     */
    public Type getType() {
        return type;
    }
}
