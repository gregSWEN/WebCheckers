package com.webcheckers.ui;

import com.webcheckers.model.Player;
import spark.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class GetSigninRoute implements Route {
    private final TemplateEngine templateEngine;
    private static final String TITLE_ATTR = "title";
    private static final String PLAYER_NAME_ATTR = "playerName";
    private static final String TITLE = "Sign-In";
    private static final String VIEW_NAME = "signin.ftl";


    public GetSigninRoute(TemplateEngine templateEngine) {
        Objects.requireNonNull(templateEngine, "templateEngine must not be null");
        this.templateEngine = templateEngine;
    }

    /**
     * Render the WebCheckers Sign in Page
     *
     * @param request
     *   the HTTP request
     * @param response
     *   the HTTP response
     *
     * @return
     *   the rendered HTML for the Home page
     */
    @Override
    public Object handle(Request request, Response response) {
        final Session httpSession = request.session();
        Player currentPlayer = httpSession.attribute("currentUser");  //get current player if there is one

        //dont go to sign in page if player is signed in
        if(currentPlayer != null) {
            response.redirect("/");
            return null;
        }
        final Map<String, Object> vm = new HashMap<>();
        vm.put(TITLE_ATTR, TITLE);
        vm.put(PLAYER_NAME_ATTR, null);
        return templateEngine.render(new ModelAndView(vm, VIEW_NAME));
    }
}
