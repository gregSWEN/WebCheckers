package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.model.GameModel;
import com.webcheckers.model.Piece;
import com.webcheckers.model.Player;
import com.webcheckers.util.Message;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Session;

import java.util.logging.Logger;

public class PostCheckTurnRoute implements Route {
    private final Gson gson = new Gson();
    private static final Logger LOG = Logger.getLogger(PostCheckTurnRoute.class.getName());

    //Constructor
    public PostCheckTurnRoute(){
        LOG.config("PostCheckTurnRoute Used");
    }

    @Override
    public Object handle(Request request, Response response) {
        final Session session = request.session();
        Player user = session.attribute("currentUser");
        GameModel game = user.getGame();

        //determine if it's the current user's turn or not
        String turn;
        if(user == game.getRedPlayer() && game.getActiveColor() == Piece.Color.RED) {
            turn = "true";  //if the user is red
        } else if(user == game.getWhitePlayer() && game.getActiveColor() == Piece.Color.WHITE) {
            turn = "true";  //if the user is white
        } else {
            turn = "false"; //if it's not their turn
        }
        return gson.toJson(Message.info(turn));
    }
}
