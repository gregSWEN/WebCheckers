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

    public Piece(Type type, Color color){
        this.type = type;
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public Type getType() {
        return type;
    }
}
