package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.GameManager;
import com.webcheckers.model.BoardView;
import com.webcheckers.model.GameModel;
import com.webcheckers.model.Move;
import com.webcheckers.model.Player;
import com.webcheckers.model.ValidateMove;
import com.webcheckers.util.Message;
import spark.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class PostValidateMoveRoute implements Route {
    private static final String VIEW_NAME = "game.ftl";
    public static final String ACTION_DATA_ATTR = "actionData";
    public static final String MESSAGE_ATTR = "message";
    private final TemplateEngine templateEngine;
    private final Gson gson = new Gson();
    private GameModel gameModel;
    public PostValidateMoveRoute(TemplateEngine templateEngine/*, GameModel gameModel*/) {
        Objects.requireNonNull(templateEngine, "templateEngine must not be null");
        //Objects.requireNonNull(gameModel, "gameModel must not be null");
        this.templateEngine = templateEngine;
        //this.gameModel = gameModel;
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        final Map<String, Object> vm = new HashMap<>();
        final Session session = request.session();
        String moveStr = request.queryParams(ACTION_DATA_ATTR);
        Player user = session.attribute(("currentUser"));
        BoardView board = user.getGame().getBoard();
        Move move = gson.fromJson(moveStr, com.webcheckers.model.Move.class);
        //BoardView board = gson.fromJson(boardStr, com.webcheckers.model.BoardView.class);
        ValidateMove validateMove = new ValidateMove(move, board);

        return gson.toJson(validateMove.isValidMove().toString());

    }
}
