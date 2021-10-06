package com.webcheckers.model;

import java.util.Iterator;
import java.util.List;

public class BoardView implements Iterable<Row>{

    private List<Row> rows;

    /**
     * initializes the boardview object
     * @param rows
     */
    public BoardView(List<Row> rows){
        this.rows = rows;
    }

    /**
     * This built in iterator method
     * is needed for the spark to iterate through the rows
     * in the boardview
     * @return
     * the new RowIterator object
     */
    @Override
    public Iterator<Row> iterator() {
        return new RowIterator(rows);
    }
}
