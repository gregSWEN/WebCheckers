package com.webcheckers.model;

import java.util.ArrayList;
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

    public BoardView flip_board(){
        List<Row> new_rows = new ArrayList<>();
        int length = rows.size();
        for (int i = 0; i < length; i++){
            Row row = rows.get(i);
            List<Space> spaces = new ArrayList<>();
            for (int k = 0; k < length; k++){
                List<Space> old_spaces = row.getSpaces();
                if (old_spaces.get(k).getPiece() == null){
                    Space new_space = new Space(k, old_spaces.get(k).getColor(), null);
                    spaces.add(new_space);
                }
                else if(old_spaces.get(k).getPiece().getColor() == Piece.Color.RED){
                    Piece piece = new Piece(old_spaces.get(k).getPiece().getType(), Piece.Color.WHITE);
                    Space new_space = new Space(k, old_spaces.get(k).getColor(), piece);
                    spaces.add(new_space);
                }
                else if(old_spaces.get(k).getPiece().getColor() == Piece.Color.WHITE){
                    Piece piece = new Piece(old_spaces.get(k).getPiece().getType(), Piece.Color.RED);
                    Space new_space = new Space(k, old_spaces.get(k).getColor(), piece);
                    spaces.add(new_space);
                }
            }
            Row new_row = new Row(i, spaces);
            new_rows.add(new_row);
        }
        BoardView new_board = new BoardView(new_rows);
        return new_board;
    }

    public Space getSpaceAt(int rowIdx, int cellIdx) { return rows.get(rowIdx).getSpace(cellIdx); }

    public void setSpaceAt(int rowIdx, int cellIdx, Piece piece) {
        rows.get(rowIdx).getSpace(cellIdx).setPiece(piece);
    }
}
