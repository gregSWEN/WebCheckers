package com.webcheckers.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Row implements Iterable<Space>{
    private int index;
    private List<Space> spaces;

    public Row(int index){
        this.index = index;
        spaces = new ArrayList<>();
    }

    public int getIndex() {
        return index;
    }

    @Override
    public Iterator<Space> iterator() {
        return new SpaceIterator(spaces);
    }
}
