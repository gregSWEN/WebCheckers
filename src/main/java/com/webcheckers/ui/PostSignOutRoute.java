package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.GameManager;
import com.webcheckers.model.GameModel;
import com.webcheckers.model.Player;
import com.webcheckers.util.Message;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Session;

import java.util.Objects;
import java.util.logging.Logger;

/**
 * This handles the user signing out. If the user is in a game,
 * it will automatically end when the user signs out.
 */
public class PostSignOutRoute implements Route {
    private final Gson gson = new Gson();
    private static final Logger LOG = Logger.getLogger(PostCheckTurnRoute.class.getName());
    GameManager gameManager;

    public PostSignOutRoute(GameManager gameManager) {
        Objects.requireNonNull(gameManager, "playerLobby must not be null");
        this.gameManager = gameManager;
        LOG.config("PostBackupRoute Used");
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        final Session session = request.session();
        Player user = session.attribute("currentUser");

        GameModel game = user.getGame();
        Message message;

        /*
        remove the current player from the player list
        set the current user to null
        If the player is in a game, resign the game
         */
        session.attribute("currentUser", null);
        gameManager.returnLobby().popPlayer(user.getName());
        if(game != null) {
            message = Message.info("resigned");
            game.resign(user);

            response.redirect("/");
            return gson.toJson(message);
        }
        response.redirect("/");
        return null;
    }
}