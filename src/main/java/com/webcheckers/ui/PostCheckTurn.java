package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.GameManager;
import com.webcheckers.model.GameModel;
import com.webcheckers.model.Player;
import com.webcheckers.model.ViewMode;
import com.webcheckers.util.Message;
import spark.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

public class PostCheckTurn implements Route{

    private Gson gson = new Gson();
    public static final String MESSAGE_ATTR = "message";
    private static final String VIEW_NAME = "game.ftl";

    GameManager manager;

    public PostCheckTurn(GameManager manager) {
        Objects.requireNonNull(manager, "gameModel must not be null");
        this.manager = manager;
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        final Map<String, Object> vm = new HashMap<>();
        final Session session = request.session();
        GameModel game = manager.getGame();
        String user = request.queryParams("currentUser");
        Player currentUser = gson.fromJson(user, com.webcheckers.model.Player.class);
        Message isMyTurn = game.checkTurn(currentUser);
        vm.put(MESSAGE_ATTR, isMyTurn);
        return new ModelAndView(vm, VIEW_NAME);
    }
}
