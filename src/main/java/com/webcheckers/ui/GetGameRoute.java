package com.webcheckers.ui;

import com.webcheckers.appl.GameManager;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.BoardView;
import com.webcheckers.model.Player;
import com.webcheckers.model.ViewMode;
import com.webcheckers.ui.GetHomeRoute;
import spark.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class GetGameRoute implements Route {
    static final String VIEW_NAME = "game.ftl";

    private final TemplateEngine templateEngine;

    /*
    initialize the getgameroute
     */
    public GetGameRoute(final TemplateEngine templateEngine){
        Objects.requireNonNull(templateEngine, "templateEngine must not be null");
        this.templateEngine = templateEngine;
    }

    /*
    handling the request and response then returning the template engine
     */
    @Override
    public Object handle(Request request, Response response) throws Exception {
        final Session httpSession = request.session();
        final GameManager gameManager = httpSession.attribute(GetHomeRoute.gameManagerKey);
        Player currentPlayer = httpSession.attribute("currentUser");
        Player activeColor = httpSession.attribute("activeColor");
        Player redPlayer = httpSession.attribute("redPlayer");
        Player whitePlayer = httpSession.attribute("whitePlayer");

        // build the view-model
        if(gameManager != null){
            BoardView board = gameManager.make_board();
            final Map<String, Object> vm = new HashMap<>();
            vm.put("title", "testing");
            vm.put("currentUser", currentPlayer);
            vm.put("activeColor", activeColor);
            vm.put("redPlayer", redPlayer);
            vm.put("whitePlayer", whitePlayer);
            vm.put("viewMode", ViewMode.PLAY);
            vm.put("board", board);

            return templateEngine.render(new ModelAndView(vm, VIEW_NAME));
        }
        else{
            response.redirect(WebServer.HOME_URL);
            return null;
        }


    }
}
