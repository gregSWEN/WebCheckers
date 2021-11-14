package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.model.*;
import com.webcheckers.util.Message;
import spark.*;

import java.util.logging.Logger;

public class PostResignGameRoute implements Route {
    private final Gson gson = new Gson();
    private static final Logger LOG = Logger.getLogger(PostCheckTurnRoute.class.getName());

    public PostResignGameRoute() {LOG.config("PostBackupRoute Used");
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        final Session session = request.session();
        Player user = session.attribute("currentUser");
        GameModel game = user.getGame();
        Message message;

        //make it so can only resign on your turn
        if(game.getRedPlayer() == user && game.getActiveColor() == Piece.Color.RED){
            message = Message.info("resigned");
            game.resign(user);
        }else if(game.getWhitePlayer() == user && game.getActiveColor() == Piece.Color.WHITE){
            message = Message.info("resigned");
            game.resign(user);
        }else{
            message = Message.error(("wait for your turn to resign"));
        }
        return gson.toJson(message);
    }

}