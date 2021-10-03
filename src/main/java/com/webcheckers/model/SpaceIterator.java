package com.webcheckers.model;

import java.util.Iterator;
import java.util.List;

public class SpaceIterator implements Iterator<Space> {

    private List<Space> spaces;
    private int index;
    private final int start = -1;
    private final int end = 8;

    public SpaceIterator(List<Space> spaces){
        this.spaces = spaces;
        this.index = 0;
    }

    @Override
    public boolean hasNext() {
        return (start < index) && (index < end);
    }

    @Override
    public Space next() {
        Space space = spaces.get(index);
        index++;
        return space;
    }
}
