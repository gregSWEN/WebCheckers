package com.webcheckers.model;

import java.util.Iterator;
import java.util.List;

public class RowIterator implements Iterator<Row> {
    private final List<Row> rows;
    private int index;
    private final int start = -1;
    private final int end = 8;

    /**
     * initializes the rowIterator object
     * @param rows
     */
    public RowIterator(List<Row> rows) {
        this.rows = rows;
        this.index = 0;
    }

    /**
     * determines if there is a
     * next element in the list
     * @return
     * a boolean type whether it is
     * true or false
     */
    @Override
    public boolean hasNext() {
        return (start < index) && (index < end);
    }

    /**
     * This retrieves the element at the
     * specified index
     * increment the index
     * @return
     * and returns the element
     */
    @Override
    public Row next() {
        Row row = rows.get(index);
        index++;
        return row;
    }
}
