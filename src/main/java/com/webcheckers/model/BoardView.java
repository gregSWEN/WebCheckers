package com.webcheckers.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BoardView implements Iterable<Row>{

    private List<Row> rows;
    private List<Move> moves;

    /**
     * initializes the boardview object
     * @param rows
     */
    public BoardView(List<Row> rows){
        this.moves = new ArrayList<>();
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

    public List<Move> getMoves() {
        return moves;
    }

    public void flush(){
        this.moves = new ArrayList<>();
    }

    public BoardView flip_board(){
        List<Row> new_rows = new ArrayList<>();
        int length = rows.size();
        for (int i = 7; i >= 0; i--){
            Row row = rows.get(i);
            List<Space> spaces = new ArrayList<>();
            for(int k = 7; k >= 0; k--){
                List<Space> old_spaces = row.getSpaces();
                if (old_spaces.get(k).getPiece() == null){
                    Space new_space = new Space(Math.abs(7-k), old_spaces.get(k).getColor(), null);
                    spaces.add(new_space);
                }else if(old_spaces.get(k).getPiece().getColor() == Piece.Color.RED){
                    Piece piece = new Piece(old_spaces.get(k).getPiece().getType(), Piece.Color.RED);
                    Space new_space = new Space(Math.abs(7-k), old_spaces.get(k).getColor(), piece);
                    spaces.add(new_space);
                }
                else if(old_spaces.get(k).getPiece().getColor() == Piece.Color.WHITE){
                    Piece piece = new Piece(old_spaces.get(k).getPiece().getType(), Piece.Color.WHITE);
                    Space new_space = new Space(Math.abs(7-k), old_spaces.get(k).getColor(), piece);
                    spaces.add(new_space);
                }
            }
            Row new_row = new Row(Math.abs(7-i), spaces);
            new_rows.add(new_row);
        }
        BoardView new_board = new BoardView(new_rows);
        return new_board;
    }

    //update the board when a move is validated and submitted
    public void update_board(Move move, boolean flipped){
        int oppositeSide;
        if(flipped){
            oppositeSide = 7;
        }else{
            oppositeSide = 0;
        }
        Position start_pos = move.getStart();
        Position end_pos = move.getEnd();
        int start_row = Math.abs(oppositeSide - start_pos.getRow());
        int start_cell = Math.abs(oppositeSide - start_pos.getCell());
        int end_row = Math.abs(oppositeSide - end_pos.getRow());
        int end_cell = Math.abs(oppositeSide - end_pos.getCell());

        Space start_space = this.rows.get(start_row).getSpace(start_cell);
        Space end_space = this.rows.get(end_row).getSpace(end_cell);

        Piece start_piece = start_space.getPiece();
        Piece end_piece;

        if(end_row == 0 || end_row == 7) {
            end_piece = new Piece(Piece.Type.KING, start_piece.getColor());
        }else{
            end_piece = new Piece(start_piece.getType(), start_piece.getColor());
        }
        start_space.setNull();
        end_space.setPiece((end_piece));

        //check if a piece captured another piece
        if(Math.abs(start_row-end_row)==2){
            int captured_row = (start_row + end_row)/2;
            int captured_cell = (start_cell + end_cell)/2;
            Space captured = this.rows.get(captured_row).getSpace(captured_cell);

            captured.setNull();
        }
    }

    public void reverse_piece(Move move, boolean flipped){
        int oppositeSide;
        if(flipped){
            oppositeSide = 7;
        }else{
            oppositeSide = 0;
        }
        Position start_pos = move.getStart();
        Position end_pos = move.getEnd();
        int start_row = Math.abs(oppositeSide - start_pos.getRow());
        int start_cell = Math.abs(oppositeSide - start_pos.getCell());
        int end_row = Math.abs(oppositeSide - end_pos.getRow());
        int end_cell = Math.abs(oppositeSide - end_pos.getCell());

        Space start_space = rows.get(start_row).getSpace(start_cell);
        Space end_space = rows.get(end_row).getSpace(end_cell);

        Piece end_piece = end_space.getPiece();
        Piece start_piece = new Piece(end_piece.getType(), end_piece.getColor());
        start_space.setPiece((start_piece));
        end_space.setPiece((null));
    }

    public Space getSpaceAt(int rowIdx, int cellIdx) { return rows.get(rowIdx).getSpace(cellIdx); }

    public void setSpaceAt(int rowIdx, int cellIdx, Piece piece) {
        rows.get(rowIdx).getSpace(cellIdx).setPiece(piece);
    }
}
