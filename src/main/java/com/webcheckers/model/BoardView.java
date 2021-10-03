package com.webcheckers.model;

import java.util.Iterator;
import java.util.List;

public class BoardView implements Iterable<Row>{

    private List<Row> rows;

    public BoardView(List<Row> rows){
        this.rows = rows;
    }

    @Override
    public Iterator<Row> iterator() {
        return new RowIterator(rows);
    }
}
