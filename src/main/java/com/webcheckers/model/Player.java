package com.webcheckers.model;

public class Player {
    private final String name;

    public Player(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof Player)) return false;
        final Player that = (Player) obj;
        return this.name.equals(that.name);
    }

    @Override
    public int hashCode() { return name.hashCode(); }
}
// This is Greg Reporting for duty