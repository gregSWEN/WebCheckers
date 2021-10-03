package com.webcheckers.model;

import java.util.Iterator;
import java.util.List;

public class RowIterator implements Iterator<Row> {
    private List<Row> rows;
    private int index;
    private final int start = -1;
    private final int end = 8;

    public RowIterator(List<Row> rows){
        this.rows = rows;
        this.index = 0;
    }

    @Override
    public boolean hasNext() {
        return (start < index) && (index < end);
    }

    @Override
    public Row next() {
        Row row = rows.get(index);
        index++;
        return row;
    }
}
