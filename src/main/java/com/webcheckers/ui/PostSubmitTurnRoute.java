package com.webcheckers.ui;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.TemplateEngine;

import java.util.Objects;

public class PostSubmitTurnRoute implements Route {
    private final TemplateEngine templateEngine;

    public PostSubmitTurnRoute(TemplateEngine templateEngine) {
        Objects.requireNonNull(templateEngine, "template engine must not be null");
        this.templateEngine = templateEngine;
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        return null;
    }
}
