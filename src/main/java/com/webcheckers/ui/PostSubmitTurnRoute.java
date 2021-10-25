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
        if(game.getActiveColor() == Piece.Color.RED){
            game.setActiveColor(Piece.Color.WHITE);
        }else{
            game.setActiveColor(Piece.Color.RED);
        }
        BoardView board = user.getGame().getBoard();


        return gson.toJson(Message.info("Good Move"));
    }
}
