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

    public Space getSpaceAt(int rowIdx, int cellIdx) { return rows.get(rowIdx).getSpace(cellIdx); }

    public void setSpaceAt(int rowIdx, int cellIdx, Piece piece) {
        rows.get(rowIdx).getSpace(cellIdx).setPiece(piece);
    }
}
