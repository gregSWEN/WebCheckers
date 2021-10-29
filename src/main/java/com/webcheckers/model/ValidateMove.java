package com.webcheckers.model;

import com.webcheckers.util.Message;

public class ValidateMove {
    private final int startRow;
    private final int startCell;
    private final int endRow;
    private final int endCell;
    private final BoardView board;
    public static final String INVALID = "Invalid move";
    public static final String CAPTURE = "You captured a piece!";
    public static final String CAPTURE_OWN = "You cannot capture your own piece";

    public ValidateMove(Move move, BoardView board) {
        this.startRow = move.getStart().getRow();
        this.startCell = move.getStart().getCell();
        this.endRow = move.getEnd().getRow();
        this.endCell = move.getEnd().getCell();
        this.board = board;
    }

    public Message isValidMove() {
        // checks if piece to move is a checker
        if (board.getSpaceAt(startRow, startCell) == null) {
            return Message.error("Need to move a checker");
        // are you moving to an occupied space?
        } else if (!board.getSpaceAt(endRow, endCell).isValid()) {
            return Message.error("Cannot move to occupied space");
        // if you are not a king, are you moving the right direction?
        } else if (endRow-startRow >= 0 && !board.getSpaceAt(startRow, startCell).isPieceKing()) {
            return Message.error("Cannot move backwards if you are not a king!");
        // are you trying to capture a piece?
        } else if (Math.abs(endRow-startRow) == 2 && Math.abs(endCell-startCell) == 2) {
            int checkerRow = (endRow + startRow) / 2;      // row of piece being captured
            int checkerCell = (endCell + startCell) / 2;   // cell of piece being captured
            // is a white piece capturing a red piece?
            if (board.getSpaceAt(startRow, startCell).isPieceWhite()) {
                if (board.getSpaceAt(checkerRow, checkerCell).isPieceRed()) {
                    return Message.info("You captured a piece!");
                }
                else
                    return Message.error("You cannot capture your own piece");
            // is a red piece capturing a white piece?
            } else {
                if (board.getSpaceAt(checkerRow, checkerCell).isPieceWhite()) {
                    return Message.info("You captured a piece!");
                }
                else
                    return Message.error("You cannot capture your own piece");
            }
        // are you making a valid single move?
        } else if (Math.abs(endRow-startRow) == 1 && Math.abs(endCell-startCell) == 1){
            return Message.info("Moved successfully");
        } else {
            return Message.error("Invalid move");
        }
    }
}
