package com.webcheckers.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Row implements Iterable<Space>{
    private int index;
    private List<Space> spaces;

    /**
     * initializes the row object
     * @param index
     * @param spaces
     */
    public Row(int index, List<Space> spaces){
        this.index = index;
        this.spaces = spaces;
    }

    /**
     *
     * @return
     * the index
     */
    public int getIndex() {
        return index;
    }

    /**
     * This is the built in iterator method that uses the spaceiterator class.
     * this is beneficial for the spark in order to iterate through the spaces
     * in the row
     * @return
     * new SpaceIterator object
     */
    @Override
    public Iterator<Space> iterator() {
        return new SpaceIterator(spaces);
    }

    public Space getSpace(int index) { return spaces.get(index); }
}
