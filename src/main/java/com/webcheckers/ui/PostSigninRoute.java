package com.webcheckers.ui;

import com.webcheckers.util.Message;
import spark.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class PostSigninRoute implements Route {
    private static final String TITLE_ATTR = "title";
    private static final String PLAYER_NAME_ATTR = "playerName";
    private static final String TITLE = "Sign-In";
    private static final String VIEW_NAME = "signin.ftl";

    private final TemplateEngine templateEngine;

    /**
     * Initialize the route with the template engine
     * @param templateEngine engine that will render the view
     */
    public PostSigninRoute(TemplateEngine templateEngine) {
        Objects.requireNonNull(templateEngine, "templateEngine must not be null");
        this.templateEngine = templateEngine;
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        //
        Map<String, Object> vm = new HashMap<>();
        vm.put("title", "Welcome!");

        // display a user message in the Home page
        vm.put("message", "esketit");

        // render the View
        return templateEngine.render(new ModelAndView(vm , "home.ftl"));
    }






}
