package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.GameManager;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.*;
import com.webcheckers.ui.GetHomeRoute;
import spark.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class GetGameRoute implements Route {
    static final String VIEW_NAME = "game.ftl";
    private final Gson gson = new Gson();
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
        String string_of_Id = request.queryParams("game_id");
        GameModel game;
        if(string_of_Id != null) {
            System.out.println(string_of_Id);
            int game_id = Integer.parseInt(string_of_Id);
            game = gameManager.getGame(game_id);
            currentPlayer.startGame(game);//THIS DOESN't START GAME IT SETS THE GAME IN PLAYER
        }else{
            game = currentPlayer.getGame();
        }

        // build the view-model for the player
        if(gameManager != null && game != null){
            final Map<String, Object> vm = new HashMap<>();

            //build the board
            vm.put("title", "testing");
            vm.put("currentUser", currentPlayer);
            vm.put("activeColor", game.getActiveColor());
            vm.put("redPlayer", game.getRedPlayer());
            vm.put("whitePlayer", game.getWhitePlayer());
            vm.put("viewMode", ViewMode.PLAY);
            if(currentPlayer == game.getRedPlayer()){
                vm.put("board", game.getBoard().flip_board());
            }else{
                vm.put("board", game.getBoard());
            }

            //check if the game ended
            if(game.getGameStatus() == true){
                final Map<String, Object> modeOptions = new HashMap<>(2);
                modeOptions.put("isGameOver", true);
                if(game.get_how_game_ended() == "resigned") {
                    modeOptions.put("gameOverMessage", game.get_loser().getName() + " resigned");
                }else{
                    modeOptions.put("gameOverMessage", "I'll fix this later");
                }
                vm.put("modeOptionsAsJSON", gson.toJson(modeOptions));
                currentPlayer.endGame();
            }

            return templateEngine.render(new ModelAndView(vm, VIEW_NAME));
        }
        else{
            response.redirect(WebServer.HOME_URL);
            return null;
        }
    }
}
