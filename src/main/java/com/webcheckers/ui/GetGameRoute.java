package com.webcheckers.ui;

import com.webcheckers.appl.GameManager;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.BoardView;
import com.webcheckers.model.Piece;
import com.webcheckers.model.Player;
import com.webcheckers.model.ViewMode;
import com.webcheckers.ui.GetHomeRoute;
import spark.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class GetGameRoute implements Route {
    public static final String VIEW_NAME_ATTR = "game.ftl";
    public static final String ENEMY_PLAYER_ATTR = "opposite";
    public static final String TITLE_ATTR = "title";
    public static final String CURRENT_USER_ATTR = "currentUser";
    public static final String ACTIVE_COLOR_ATTR = "activeColor";
    public static final String RED_PLAYER_ATTR = "redPlayer";
    public static final String WHITE_PLAYER_ATTR  = "whitePlayer";
    public static final String VIEW_MODE_ATTR = "viewMode";
    public static final String BOARD_ATTR = "board";

    private static final String TITLE = "Welcome to the game!";

    private final TemplateEngine templateEngine;
    private final GameManager gameManager;


    public GetGameRoute(final GameManager gameManager, final TemplateEngine templateEngine){
        Objects.requireNonNull(templateEngine, "templateEngine must not be null");
        this.templateEngine = templateEngine;
        this.gameManager = gameManager;
    }


    /**
     *
     * @param request
     * the HTTP request
     * @param response
     * the HTTP response
     * @return
     * THE render of the game board displayed as a grid with the colored checkers
     * @throws Exception
     */
    @Override
    public Object handle(Request request, Response response) throws Exception {

        final Session httpSession = request.session();
        final GameManager gameManager = httpSession.attribute(GetHomeRoute.gameManagerKey);
        Player currentPlayer = httpSession.attribute("currentUser");
        Player activeColor = httpSession.attribute("activeColor");
        Player redPlayer = httpSession.attribute("redPlayer");
        Player whitePlayer = httpSession.attribute("whitePlayer");
        String Enemy = request.queryParams(ENEMY_PLAYER_ATTR);
        Player enemyPlayer = gameManager.returnLobby().getPlayer(Enemy);

        // build the view-model
        if(gameManager != null){
            BoardView board = gameManager.make_board();
            final Map<String, Object> vm = new HashMap<>();
            vm.put(TITLE_ATTR, TITLE);
            vm.put(CURRENT_USER_ATTR, currentPlayer);
            vm.put(ACTIVE_COLOR_ATTR, Piece.Color.RED);
            vm.put(RED_PLAYER_ATTR, currentPlayer);
            vm.put(WHITE_PLAYER_ATTR, enemyPlayer);
            vm.put(VIEW_MODE_ATTR, ViewMode.PLAY);
            vm.put(BOARD_ATTR, board);

            return templateEngine.render(new ModelAndView(vm, VIEW_NAME_ATTR));
        }
        else{
            response.redirect(WebServer.HOME_URL);
            return null;
        }
    }
}
