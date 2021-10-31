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

    public Color getOppositeColor(){
        if(this.color == Color.RED){
            return Color.WHITE;
        }else{
            return Color.RED;
        }
    }

    @Override
    public String toString(){
        return "type: " + this.type + "color: " + this.color;
    }
}
