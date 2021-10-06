package com.webcheckers.ui;

import com.webcheckers.appl.GameManager;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Player;
import com.webcheckers.util.Message;
import spark.*;
import static spark.Spark.halt;

import java.util.*;

import java.util.Objects;

public class PostSigninRoute implements Route {
    private static final String TITLE_ATTR = "title";
    private static final String PLAYER_NAME_ATTR = "playerName";
    private static final String MESSAGE_ATTR = "message";
    private static final String MESSAGE_TYPE_ATTR = "messageType";

    private static final String TITLE = "Sign-In";
    private static final String VIEW_NAME = "signin.ftl";
    private static final String LIST_PLAYERS = "available_players";
    private static final String CURRENT_USER = "currentUser";

    private final TemplateEngine templateEngine;
    private final GameManager gameManager;

    /**
     * Initialize the route with the template engine
     * @param templateEngine engine that will render the view
     */
    public PostSigninRoute(GameManager gameManager, TemplateEngine templateEngine) {
        Objects.requireNonNull(templateEngine, "templateEngine must not be null");
        Objects.requireNonNull(gameManager, "playerLobby must not be null");
        this.templateEngine = templateEngine;
        this.gameManager = gameManager;
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        final Map<String, Object> vm = new HashMap<>();

        final Session session = request.session();

        final String nameString = request.queryParams(PLAYER_NAME_ATTR);
        Message message = gameManager.returnLobby().addPlayer(nameString);

        //if a player name is invalid, return to sign-in page with error msg
        if(message.getType() == Message.Type.ERROR) {
            vm.put(TITLE_ATTR, TITLE);
            vm.put(PLAYER_NAME_ATTR, null);
            vm.put(MESSAGE_ATTR, message);
            return templateEngine.render(new ModelAndView(vm , "signin.ftl"));
        }

        //set current player in session
        session.attribute(CURRENT_USER, gameManager.returnLobby().getPlayer((nameString)));
        session.attribute(GetHomeRoute.gameManagerKey, gameManager);
        //return user to home page as a player
        vm.put(TITLE_ATTR, "Welcome!");
        vm.put(MESSAGE_ATTR, message);
        vm.put(LIST_PLAYERS, gameManager.returnLobby().listOtherPlayers(nameString));
        vm.put(CURRENT_USER, gameManager.returnLobby().getPlayer((nameString)));
        return templateEngine.render(new ModelAndView(vm , "home.ftl"));

    }




}
