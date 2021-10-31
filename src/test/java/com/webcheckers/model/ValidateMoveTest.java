package com.webcheckers.model;

import static org.junit.jupiter.api.Assertions.*;

import com.webcheckers.appl.GameManager;
import com.webcheckers.util.Message;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.LinkedList;

/**
 * Tests the functionality of the ValidateMove class, which
 * is responsible for making sure an input move is legal
 */
@Tag("Model-tier")
public class ValidateMoveTest {
    private BoardView board;
    private ValidateMove validateMove;
    private final Piece RED_PIECE = new Piece(Piece.Type.SINGLE, Piece.Color.RED);
    private final Piece RED_KING = new Piece(Piece.Type.KING, Piece.Color.RED);
    private final Piece WHITE_PIECE = new Piece(Piece.Type.SINGLE, Piece.Color.WHITE);
    private final Piece WHITE_KING = new Piece(Piece.Type.KING, Piece.Color.WHITE);
    private static final String INVALID = "Invalid move";
    private static final String CAPTURE = "You captured a piece!";
    private static final String CAPTURE_OWN = "You cannot capture your own piece";
    private static final String NO_MOVE = "Need to move a checker";
    private static final String OCCUPIED = "Cannot move to occupied space";
    private static final String BACKWARDS = "Cannot move backwards if you are not a king!";
    private static final String SUCCESS = "Moved successfully";

//
//    @Test
//    public void testSingleMove() {
//        board = GameManager.make_board();
//        board.setSpaceAt(4, 3, RED_PIECE);
//        Position start = new Position(4, 3);
//        Position end = new Position(3, 4);
//        Move move = new Move(start, end);
//
//        validateMove = new ValidateMove(move, board);
//        assertEquals(SUCCESS, validateMove.isValidMove().getText());
//
//        board.setSpaceAt(3, 4, WHITE_PIECE);
//        validateMove = new ValidateMove(move, board);
//        assertEquals(OCCUPIED, validateMove.isValidMove().getText());
//
//        board = GameManager.make_board();
//        board.setSpaceAt(3,4, RED_PIECE);
//        start = new Position(3, 4);
//        end = new Position(4, 3);
//        move = new Move(start, end);
//        validateMove = new ValidateMove(move, board);
//        assertEquals(BACKWARDS, validateMove.isValidMove().getText());
//
//        board.setSpaceAt(3, 4, RED_KING);
//        validateMove = new ValidateMove(move, board);
//        assertEquals(SUCCESS, validateMove.isValidMove().getText());
//    }
//
//    @Test
//    public void testCapture() {
//        board = GameManager.make_board();
//        board.setSpaceAt(5, 4, RED_PIECE);
//        board.setSpaceAt(4, 3, WHITE_PIECE);
//        Position start = new Position(5, 4);
//        Position end = new Position(3, 2);
//        Move move = new Move(start, end);
//
//        validateMove = new ValidateMove(move, board);
//        assertEquals(CAPTURE, validateMove.isValidMove().getText());
//
//        board.setSpaceAt(4, 3, RED_PIECE);
//        validateMove = new ValidateMove(move, board);
//        assertEquals(CAPTURE_OWN, validateMove.isValidMove().getText());
//
//        board = GameManager.make_board();
//        board.setSpaceAt(3, 2, RED_PIECE);
//        board.setSpaceAt(4, 1, WHITE_PIECE);
//        board.setSpaceAt(5, 0, null);
//        start = new Position(3, 2);
//        end = new Position(5, 0);
//        move = new Move(start, end);
//        validateMove = new ValidateMove(move, board);
//        assertEquals(BACKWARDS, validateMove.isValidMove().getText());
//
//        board.setSpaceAt(3, 2, RED_KING);
//        validateMove = new ValidateMove(move, board);
//        assertEquals(CAPTURE, validateMove.isValidMove().getText());
//    }
}
