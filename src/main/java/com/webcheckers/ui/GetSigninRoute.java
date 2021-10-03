package com.webcheckers.ui;

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

    @Override
    public Object handle(Request request, Response response) throws Exception {
        final Session httpSession =request.session();
        final Map<String, Object> vm = new HashMap<>();
        vm.put(TITLE_ATTR, TITLE);
        vm.put(PLAYER_NAME_ATTR, null);
        return templateEngine.render(new ModelAndView(vm, VIEW_NAME));
    }
}
