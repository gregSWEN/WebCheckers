package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.model.*;
import com.webcheckers.util.Message;
import spark.*;

import java.util.Objects;

public class PostSubmitTurnRoute implements Route {
    private final TemplateEngine templateEngine;
    private final Gson gson = new Gson();

    public PostSubmitTurnRoute(TemplateEngine templateEngine) {
        Objects.requireNonNull(templateEngine, "template engine must not be null");
        this.templateEngine = templateEngine;
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        final Session session = request.session();
        Player user = session.attribute("currentUser");
        GameModel game = user.getGame();
        BoardView board = user.getGame().getBoard();
        Move move = user.peekMove();

        //make move when player submits
        //change game turn to the other player
        if(game.getActiveColor() == Piece.Color.RED) {
            board.update_board(move, true);
            game.setActiveColor(Piece.Color.WHITE);
        }else{
            board.update_board(move, false);
            game.setActiveColor(Piece.Color.RED);
        }

        //give player another move to capture multiple pieces.
        if(user.getMultiCapture()){
            if(game.getActiveColor() == Piece.Color.RED) {
                game.setActiveColor(Piece.Color.WHITE);
            }else{
                game.setActiveColor(Piece.Color.RED);
            }
        }
        user.madeTurn(false);
        user.setMultiCapture(false);
        return gson.toJson(Message.info("Good Move"));
    }
}
