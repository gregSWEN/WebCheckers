package com.webcheckers.model;

import java.util.Iterator;
import java.util.List;

public class SpaceIterator implements Iterator<Space> {

    private List<Space> spaces;
    private int index;
    private final int start = -1;
    private final int end = 8;

    /**
     * initializes the spaceiterator
     * @param spaces
     */
    public SpaceIterator(List<Space> spaces){
        this.spaces = spaces;
        this.index = 0;
    }

    /**
     * this determines if there
     * is another element next
     * in the list
     * @return
     * a boolean statement of true or false
     */
    @Override
    public boolean hasNext() {
        return (start < index) && (index < end);
    }

    /**
     * this grabs the element at the
     * specified index
     * increment the index
     * @return
     * the element
     */
    @Override
    public Space next() {
        Space space = spaces.get(index);
        index++;
        return space;
    }
}
