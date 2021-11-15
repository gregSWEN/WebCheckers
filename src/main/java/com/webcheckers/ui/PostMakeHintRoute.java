package com.webcheckers.ui;

import com.webcheckers.appl.GameManager;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.TemplateEngine;

import java.util.Objects;

/**
 * This class handles the user asking for a hint, then sending
 * that hint as a Message.
 */
public class PostMakeHintRoute implements Route {
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
        return null;
    }
}
