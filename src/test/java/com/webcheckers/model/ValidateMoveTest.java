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


    @Test
    public void testSingleMove() {
        board = GameManager.make_board();
        board.setSpaceAt(3, 3, RED_PIECE);
        Position start = new Position(3, 3);
        Position end = new Position(2, 2);
        Move move = new Move(start, end);

        validateMove = new ValidateMove(move, board);
        assertEquals(validateMove.isValidMove().getText(), SUCCESS);

        board.setSpaceAt(2, 2, WHITE_PIECE);
        validateMove = new ValidateMove(move, board);
        assertEquals(validateMove.isValidMove().getText(), OCCUPIED);

        end = new Position(4, 4);
        move = new Move(start, end);
        validateMove = new ValidateMove(move, board);
        assertEquals(validateMove.isValidMove().getText(), BACKWARDS);

        board.setSpaceAt(3, 3, RED_KING);
        validateMove = new ValidateMove(move, board);
        assertEquals(validateMove.isValidMove().getText(), SUCCESS);
    }

    @Test
    public void testCapture() {
        board = GameManager.make_board();
        board.setSpaceAt(3, 3, RED_PIECE);
        board.setSpaceAt(4, 4, WHITE_PIECE);
        Position start = new Position(3, 3);
        Position end = new Position(5, 5);
        Move move = new Move(start, end);
        validateMove = new ValidateMove(move, board);
        assertNotEquals(validateMove.isValidMove().getText(), CAPTURE);
        board.setSpaceAt(4, 4, RED_PIECE);
        assertEquals(validateMove.isValidMove().getText(), CAPTURE_OWN);
        board.setSpaceAt(2, 2, WHITE_PIECE);
        end = new Position(1, 1);
        move = new Move(start, end);
        validateMove = new ValidateMove(move, board);
        assertEquals(validateMove.isValidMove().getText(), BACKWARDS);
        board.setSpaceAt(3, 3, RED_KING);
        assertEquals(validateMove.isValidMove().getText(), SUCCESS);
    }
}
