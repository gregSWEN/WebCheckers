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

import java.util.*;
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
    public Object handle(Request request, Response response) {
        final Session session = request.session();
        Player user = session.attribute("currentUser");
        Message message;

        /*
        remove the current player from the player list
        set the current user to null
        If the player is in a game, resign the game
         */
        Set<GameModel> gamesSet = user.getPlayerGames();
        List<GameModel> games = new ArrayList<>(gamesSet);

        if(!games.isEmpty()) {
            for(GameModel s: games) {
                s.resign(user);
            }
            message = Message.info("resigned");
            gameManager.returnLobby().popPlayer(user.getName());
            session.attribute("currentUser", null);
            response.redirect("/");
            return gson.toJson(message);
        }
        gameManager.returnLobby().popPlayer(user.getName());
        session.attribute("currentUser", null);
        response.redirect("/");
        return null;
    }
}