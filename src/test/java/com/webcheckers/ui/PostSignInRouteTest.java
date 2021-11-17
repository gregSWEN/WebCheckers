//package com.webcheckers.ui;
//
//import com.webcheckers.appl.PlayerLobby;
//import com.webcheckers.model.Player;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Tag;
//import org.junit.jupiter.api.Test;
//import com.webcheckers.appl.GameManager;
//import com.webcheckers.ui.PostSigninRoute;
//import spark.*;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
////
//@Tag("UI-tier")
//public class PostSignInRouteTest {
//    private TemplateEngine templateEngine;
//    private Response response;
//    private Request request;
//    private Session session;
//    private PostSigninRoute CuT;
//    private String username = "test";
//    private String usernameInvalid = "!!test";
//    private PlayerLobby playerLobby;
//
//    @BeforeEach
//    public void stage() {
//        response = mock(Response.class);
//        request = mock(Request.class);
//        session = mock(Session.class);
//        playerLobby = mock(PlayerLobby.class);
//        when(request.session()).thenReturn(session);
//        templateEngine = mock(TemplateEngine.class);
//        GameManager gameManager = new GameManager();
//        CuT = new PostSigninRoute(gameManager, templateEngine);
//    }
//    @Test
//    public void invalidNameTest() throws Exception {
//        when(request.queryParams("playerName")).thenReturn(usernameInvalid);
//        when(session.attribute("currentUser")).thenReturn(usernameInvalid);
//        TemplateEngineTester tester = new TemplateEngineTester();
//        when(templateEngine.render(any(ModelAndView.class))).thenAnswer(tester.makeAnswer());
//        CuT.handle(request, response);
//        tester.assertViewModelAttribute("currentUser", null);
//        tester.assertViewModelIsaMap();
//        tester.assertViewName("signin.ftl");
//    }
//
//    @Test
//    public void validNameTest() throws Exception {
//        playerLobby.addPlayer(username);
//        when(request.queryParams("playerName")).thenReturn(username);
//        when(session.attribute("currentUser")).thenReturn(username);
//        TemplateEngineTester tester = new TemplateEngineTester();
//        when(templateEngine.render(any(ModelAndView.class))).thenAnswer(tester.makeAnswer());
//        CuT.handle(request, response);
//        tester.assertViewName("home.ftl");
//    }
//}