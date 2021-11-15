package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.GameManager;
import com.webcheckers.model.*;
import com.webcheckers.util.Message;
import spark.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * This class tests the legality of a move and returns a message on
 * whether the move was successful. It also checks if another capture
 * is possible.
 */
public class PostValidateMoveRoute implements Route {
    private static final String VIEW_NAME = "game.ftl";
    public static final String ACTION_DATA_ATTR = "actionData";
    public static final String MESSAGE_ATTR = "message";
    private final TemplateEngine templateEngine;
    private final Gson gson = new Gson();
    private GameModel gameModel;
    private GameManager manager;
    public PostValidateMoveRoute(TemplateEngine templateEngine, GameManager manager/*, GameModel gameModel*/) {
        Objects.requireNonNull(templateEngine, "templateEngine must not be null");
        //Objects.requireNonNull(gameModel, "gameModel must not be null");
        this.templateEngine = templateEngine;
        this.manager = manager;
        //this.gameModel = gameModel;
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        final Map<String, Object> vm = new HashMap<>();
        final Session session = request.session();
        gameModel = manager.getGame();
        String moveStr = request.queryParams(ACTION_DATA_ATTR);
        Player user = session.attribute(("currentUser"));
        GameModel game = user.getGame();
        BoardView board = user.getGame().getBoard();
        Move move = gson.fromJson(moveStr, com.webcheckers.model.Move.class);
        //BoardView board = gson.fromJson(boardStr, com.webcheckers.model.BoardView.class);
        ValidateMove validateMove;

        if(user.getMadeMove() == false) {
            if (game.getActiveColor() == Piece.Color.RED) {
                validateMove = new ValidateMove(move, board.flip_board());
            } else {
                validateMove = new ValidateMove(move, board);
            }

            if (validateMove.isValidMove().getType() == Message.Type.INFO) {
                user.addMove(move);
                user.madeTurn(true);
                if(validateMove.isValidMove().getText().equals("You can Capture another Piece, submit first")){
                    user.setMultiCapture(true);
                }

                //System.out.println(move);
            }
            return gson.toJson(validateMove.isValidMove());
        }else{
            return gson.toJson(Message.error("Already made a move"));
        }

    }
}
