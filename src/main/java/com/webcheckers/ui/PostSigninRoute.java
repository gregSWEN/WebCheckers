package com.webcheckers.ui;

import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Player;
import com.webcheckers.util.Message;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.TemplateEngine;

import java.util.Objects;

public class PostSigninRoute implements Route {
    private static final String TITLE_ATTR = "title";
    private static final String PLAYER_NAME_ATTR = "playerName";
    private static final String MESSAGE_ATTR = "message";
    private static final String MESSAGE_TYPE_ATTR = "messageType";

    private static final String TITLE = "Sign-In";
    private static final String VIEW_NAME = "signin.ftl";

    private final TemplateEngine templateEngine;
    private final PlayerLobby playerLobby;

    /**
     * Initialize the route with the template engine
     * @param templateEngine engine that will render the view
     */
    public PostSigninRoute(PlayerLobby playerLobby, TemplateEngine templateEngine) {
        Objects.requireNonNull(templateEngine, "templateEngine must not be null");
        Objects.requireNonNull(playerLobby, "playerLobby must not be null");
        this.templateEngine = templateEngine;
        this.playerLobby = playerLobby;
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        return null;
    }






}
