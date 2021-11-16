//package com.webcheckers.ui;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.eq;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//import com.webcheckers.util.Message;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Tag;
//import org.junit.jupiter.api.Test;
//
//import com.webcheckers.appl.GameManager;
//
//import spark.HaltException;
//import spark.ModelAndView;
//import spark.Request;
//import spark.Response;
//import spark.Session;
//import spark.TemplateEngine;
//
//public class GetHomeRouteTest {
//    private GetHomeRoute CuT;
//
//    // friendly objects
//    private GameManager gameManager;
//
//    // mock objects
//    private Request request;
//    private Session session;
//    private TemplateEngine engine;
//    private Response response;
//
//    @BeforeEach
//    public void setup() {
//        request = mock(Request.class);
//        session = mock(Session.class);
//        when(request.session()).thenReturn(session);
//        response = mock(Response.class);
//        engine = mock(TemplateEngine.class);
//
//        // create a unique CuT for each test
//        // the GameCenter is friendly but the engine mock will need configuration
//        gameManager = new GameManager();
//        CuT = new GetHomeRoute(engine, gameManager);
//    }
//    @Test
//    public void new_session() {
//        final TemplateEngineTester testHelper = new TemplateEngineTester();
//        when(engine.render(any(ModelAndView.class))).thenAnswer(testHelper.makeAnswer());
//
//        CuT.handle(request, response);
//
//        //testHelper.assertViewModelAttribute("title", "Welcome!");
//        //testHelper.assertViewModelAttribute("num_of_players", Message.info("Current Number of Players: "+0));
//    }
//}
//
package com.webcheckers.ui;

import com.webcheckers.appl.GameManager;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import spark.*;

import com.webcheckers.util.Message;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Tester for {@link GetHomeRoute} class.
 *
 * @author Caleb Eldridge
 */
@Tag("UI-tier")
public class GetHomeRouteTest {
    // Private Fields
    private static final String TITLE_ATTR = "title";
    private static final String CURRENT_USER = "currentUser";
    private TemplateEngine templateEngine;
    private Response response;
    private Request request;
    private Session session;
    private GetHomeRoute CuT;


    @Test
    public void handleTest() {
        // Create mock session, response, request, and templateEngine
        response = mock(Response.class);
        request = mock(Request.class);
        session = mock(Session.class);
        templateEngine = mock(TemplateEngine.class);
        GameManager gameManager = new GameManager();

        //Stage mock session
        when(request.session()).thenReturn(session);
        CuT = new GetHomeRoute(templateEngine, gameManager);
        TemplateEngineTester tester = new TemplateEngineTester();
        when(templateEngine.render(any(ModelAndView.class))).thenAnswer(tester.makeAnswer());
        CuT.handle(request, response);

        //Test ViewModel and its attributes
        tester.assertViewModelExists();
        tester.assertViewModelIsaMap();
        tester.assertViewModelAttribute(TITLE_ATTR, "Welcome!");
        tester.assertViewModelAttribute(CURRENT_USER, null);
    }
}
