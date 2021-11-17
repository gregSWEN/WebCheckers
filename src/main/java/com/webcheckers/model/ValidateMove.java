package com.webcheckers.model;

import com.webcheckers.util.Message;

import java.util.*;

public class ValidateMove {
    private final Move move;
    private final int startRow;
    private final int startCell;
    private final int endRow;
    private final int endCell;
    private final BoardView board;
    private final Piece piece;
    private List<Move> moves;
    private final Piece.Color currentColor;
    public static final String INVALID = "Invalid move";
    public static final String CAPTURE = "You captured a piece!";
    public static final String CAPTURE_OWN = "You cannot capture your own piece";

    public ValidateMove(Move move, BoardView board) {
        this.move = move;
        this.startRow = move.getStart().getRow();
        this.startCell = move.getStart().getCell();
        this.endRow = move.getEnd().getRow();
        this.endCell = move.getEnd().getCell();
        this.board = board;
        this.piece = board.getSpaceAt(startRow, startCell).getPiece();
        if(this.piece == null){
            this.currentColor = null;
        }else {
            this.currentColor = piece.getColor();
        }
    }

    public ValidateMove(BoardView board, Piece.Color currentColor){
        this.move = null;
        this.startRow = 0;
        this.startCell = 0;
        this.endRow = 0;
        this.endCell = 0;
        this.board = board;
        this.piece = null;
        this.currentColor = currentColor;
    }

    public Message hint_message(){
        Message message;
        String result = "A space is derived from rows 1-8 and columns 1-8\n" +
                "for example (1,1) will be the top left corner";
        List<Move> captures = canCapture();
        List<Move> possibleMoves = possibleMoves();
        if(captures.size() > 0){
            for (Move move: captures){
                result += "You can go from (" + (move.getStart().getRow() + 1) + ", " + (move.getStart().getCell() + 1) +
                        ") to (" + (move.getEnd().getRow() + 1) + ", " + (move.getEnd().getCell() + 1) + ")";
            }
        }
        else{
            for(Move move: possibleMoves){
                result += "You can go from (" + (move.getStart().getRow() + 1) + ", " + (move.getStart().getCell() + 1) +
                        ") to (" + (move.getEnd().getRow() + 1) + ", " + (move.getEnd().getCell() + 1) + ")";
            }
        }
        message = Message.info(result);
        return message;
    }


    public Message isValidMove() {
        int kingSpaceCheck;
        if(this.piece.getType() == Piece.Type.KING){
            kingSpaceCheck = 4;
        }else{
            kingSpaceCheck = 2;
        }
        List<Move> captureMove = canCapture();

        if(captureMove.size() != 0 && !captureMove.contains(move)){
            return Message.error("Must play capture move");
        }
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
            //check if there is a piece to be captured
            if(board.getSpaceAt(checkerRow, checkerCell).getPiece() == null){
                return Message.error("Invalid move");
            }
            // is a white piece capturing a red piece?
            if (board.getSpaceAt(startRow, startCell).isPieceWhite()) {
                if (board.getSpaceAt(checkerRow, checkerCell).isPieceRed()) {
                    Move move = PieceCanCaptureMulti(endRow, endCell, kingSpaceCheck, currentColor);
                    if(move != null){
                        return Message.info("You can Capture another Piece, submit first");
                    }
                    return Message.info("You captured a piece!");
                }
                else
                    return Message.error("You cannot capture your own piece");
            // is a red piece capturing a white piece?
            } else {
                if (board.getSpaceAt(checkerRow, checkerCell).isPieceWhite()) {
                    Move move = PieceCanCaptureMulti(endRow, endCell, kingSpaceCheck, currentColor);
                    if(move != null){
                        return Message.info("You can Capture another Piece, submit first");
                    }
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


    private Move PieceCanCapture(int row, int cell, int kingSpaceCheck){
        int[] capture1row = {-1, -1, 1, 1};
        int[] capture1cell = {-1, 1, -1, 1};
        int[] capture2row = { -2, -2, 2, 2};
        int[] capture2cell = {-2, 2, -2, 2};

        for(int i = 0; i<kingSpaceCheck; i++){
            try {
                Space OneBlockAwaySpace = board.getSpaceAt(row + capture1row[i], cell + capture1cell[i]);
                Space TwoBlockAwaySpace = board.getSpaceAt(row + capture2row[i], cell + capture2cell[i]);
                if (OneBlockAwaySpace.getPiece().getColor() == board.getSpaceAt(row,cell).getPiece().getOppositeColor()) {
                    if (TwoBlockAwaySpace.getPiece() == null) {
                        Position start = new Position(row, cell);
                        Position end = new Position(row + capture2row[i], cell + capture2cell[i]);
                        return new Move(start, end);
                    }
                }
            }catch(NullPointerException e){
                //pass
            }catch(IndexOutOfBoundsException e){
                //pass
            }
        }
        return null;
    }

    //check on the board if a capture can be made
    private List<Move> canCapture(){
        List<Move> captureMoves = new ArrayList<>();
        int kingSpaceCheck;
        /*
         * iterate through every spot on the board
         * if there's a spot that has the current user's piece
         * check if that piece has a piece next to it that can be captured
         *
         * Only check what the piece can move to
         * For example a regular piece can only move to 2 spaces
         * so it only checks for 2 spaces, while a king piece
         * has to check for 4 available spots
         */
        for (int row = 0; row < 8; row++) {
            for (int cell = 0; cell < 8; cell++) {
                Space space = board.getSpaceAt(row, cell);
                try{
                    if(space.getPiece().getColor() == currentColor){
                        if(space.getPiece().getType() == Piece.Type.KING){
                            kingSpaceCheck = 4;
                        }else{
                            kingSpaceCheck = 2;
                        }
                        Move tempMove = PieceCanCapture(row, cell, kingSpaceCheck);
                        if(tempMove != null){
                            captureMoves.add(tempMove);
                        }
                    }
                }catch(NullPointerException e){
                    //pass
                }
            }
        }
        return captureMoves;
    }

    private Move PieceCanCaptureMulti(int row, int cell, int kingSpaceCheck, Piece.Color color){
        int[] capture1row = {-1, -1, 1, 1};
        int[] capture1cell = {-1, 1, -1, 1};
        int[] capture2row = { -2, -2, 2, 2};
        int[] capture2cell = {-2, 2, -2, 2};

        for(int i = 0; i<kingSpaceCheck; i++){
            try {
                Space OneBlockAwaySpace = board.getSpaceAt(row + capture1row[i], cell + capture1cell[i]);
                Space TwoBlockAwaySpace = board.getSpaceAt(row + capture2row[i], cell + capture2cell[i]);
                if (OneBlockAwaySpace.getPiece().getColor() != color) {
                    if (TwoBlockAwaySpace.getPiece() == null) {
                        Position start = new Position(row, cell);
                        Position end = new Position(row + capture2row[i], cell + capture2cell[i]);
                        return new Move(start, end);
                    }
                }
            }catch(NullPointerException e){
                //pass
            }catch(IndexOutOfBoundsException e){
                //pass
            }
        }
        return null;
    }

    private List<Move> pieceCanMoveSingle(Space space, int row){
        List<Move> moves = new ArrayList<>();
        int startCol = space.getCellIdx();
        if(row - 1 > -1 && startCol - 1 > -1){
            Position start = new Position(row, startCol);
            Position end = new Position(row - 1, startCol - 1);
            Move move = new Move(start, end);
            moves.add(move);
        }
        if(row - 1 > -1 && startCol + 1 < 8){
            Position start = new Position(row, startCol);
            Position end = new Position(row - 1, startCol + 1);
            Move move = new Move(start, end);
            moves.add(move);
        }
        return moves;
    }

    private List<Move> possibleMoves(){
        List<Move> moves = new ArrayList<>();
        int boardLength = 8;

        for(int i = 0; i < boardLength; i++){
            for(int k = 0; k < boardLength; k++){
                Space space = board.getSpaceAt(i, k);
                if(space.getPiece().getColor() == currentColor){
                    if(space.getPiece().getType() == Piece.Type.SINGLE){
                        List<Move> singleMoves = pieceCanMoveSingle(space, i);
                        for (Move move: singleMoves){
                            moves.add(move);
                        }
                    }
                }
            }
        }
        return moves;
    }


}
