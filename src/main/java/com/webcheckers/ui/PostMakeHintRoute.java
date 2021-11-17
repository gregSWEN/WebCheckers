package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.GameManager;
import com.webcheckers.util.Message;
import spark.*;

import java.util.Objects;

/**
 * This class handles the user asking for a hint, then sending
 * that hint as a Message.
 */
public class PostMakeHintRoute implements Route {
    private final Gson gson = new Gson();
    private final TemplateEngine templateEngine;
    private final GameManager gameManager;

    public PostMakeHintRoute(TemplateEngine templateEngine, GameManager gameManager) {
        Objects.requireNonNull(templateEngine, "templateEngine must not be null");
        Objects.requireNonNull(gameManager, "gameManager must not be null");
        this.templateEngine = templateEngine;
        this.gameManager = gameManager;
    }
    @Override
    public Object handle(Request request, Response response) throws Exception {
        final Session session = request.session();
        Message message;
        message = Message.info("button works :)");
        return gson.toJson(message);
    }
}
