package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.model.*;
import com.webcheckers.util.Message;
import spark.*;

import java.util.logging.Logger;

public class PostBackupRoute implements Route{
    private final Gson gson = new Gson();
    private static final Logger LOG = Logger.getLogger(PostCheckTurnRoute.class.getName());

    public PostBackupRoute(){
        LOG.config("PostBackupRoute Used");
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        final Session session = request.session();
        Player user = session.attribute("currentUser");
        user.madeTurn(false);
        return gson.toJson(Message.info("Successfully backup move"));

    }
}