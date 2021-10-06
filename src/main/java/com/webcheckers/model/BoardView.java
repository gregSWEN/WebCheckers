package com.webcheckers.model;

import java.util.Iterator;
import java.util.List;

public class BoardView implements Iterable<Row>{

    private List<Row> rows;

    /*
    initializes the boardview object
     */
    public BoardView(List<Row> rows){
        this.rows = rows;
    }

    /*
    the built in iterator method that uses the
    RowIterator in order for the game.ftl file to
    traverse the rows in Boardview
     */
    @Override
    public Iterator<Row> iterator() {
        return new RowIterator(rows);
    }
}
