package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.model.BoardView;
import com.webcheckers.model.Move;
import com.webcheckers.model.ValidateMove;
import spark.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class PostValidateMoveRoute implements Route {
    private static final String VIEW_NAME = "game.ftl";
    public static final String ACTION_DATA_ATTR = "actionData";
    private final TemplateEngine templateEngine;
    private final Gson gson = new Gson();

    public PostValidateMoveRoute(TemplateEngine templateEngine) {
        Objects.requireNonNull(templateEngine, "templateEngine must not be null");
        this.templateEngine = templateEngine;
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        final Map<String, Object> vm = new HashMap<>();
        final Session session = request.session();
        String moveStr = request.queryParams(ACTION_DATA_ATTR);
        String boardStr = request.queryParams("board");
        Move move = gson.fromJson(moveStr, com.webcheckers.model.Move.class);
        BoardView board = gson.fromJson(boardStr, com.webcheckers.model.BoardView.class);
        ValidateMove validateMove = new ValidateMove(move, board);
        return new ModelAndView(vm, VIEW_NAME);
    }
}
