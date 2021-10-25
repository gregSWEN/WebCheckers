package com.webcheckers.ui;

import com.webcheckers.appl.GameManager;
import com.webcheckers.model.GameModel;
import com.webcheckers.model.Player;
import com.webcheckers.model.ViewMode;
import spark.*;

import java.util.HashMap;
import java.util.Map;

public class PostGameRoute implements Route {
    static final String VIEW_NAME = "game.ftl";
    static final String ENEMY_PLAYER = "opposite";

    private final TemplateEngine templateEngine;
    private final GameManager gameManager;

    public PostGameRoute(final GameManager gameManager, final TemplateEngine templateEngine){
        this.gameManager = gameManager;
        this.templateEngine = templateEngine;
    }

    @Override
    public Object handle(Request request, Response response)throws Exception{
        final Session session = request.session();

        //get current player and enemy player
        Player player = session.attribute(PostSigninRoute.CURRENT_USER);
        String Enemy = request.queryParams(ENEMY_PLAYER);
        Player enemyPlayer = gameManager.returnLobby().getPlayer(Enemy);

        //make the game if both players aren't in a game
        //and return to home if players are in game
        if(player.getGame() == null && enemyPlayer.getGame() == null){
            final Map<String, Object> vm = new HashMap<>();
            GameModel game = new GameModel(player, enemyPlayer);
            gameManager.setGame(game);
            vm.put("title", "testing");
            vm.put("currentUser", player);
            vm.put("activeColor", game.getActiveColor());
            vm.put("redPlayer", game.getRedPlayer());
            vm.put("whitePlayer", game.getWhitePlayer());
            vm.put("viewMode", ViewMode.PLAY);
            if(player == game.getRedPlayer()){
                vm.put("board", game.getBoard().flip_board());
            }else{
                vm.put("board", game.getBoard());
            }


            return templateEngine.render(new ModelAndView(vm , VIEW_NAME));
        }else{
            response.redirect("/");
            return null;
        }
    }
}
