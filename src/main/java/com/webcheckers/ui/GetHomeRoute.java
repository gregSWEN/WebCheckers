package com.webcheckers.ui;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

import com.webcheckers.appl.GameManager;
import com.webcheckers.model.Player;
import spark.*;

import com.webcheckers.util.Message;

/**
 * The UI Controller to GET the Home page.
 *
 * @author <a href='mailto:bdbvse@rit.edu'>Bryan Basham</a>
 */
public class GetHomeRoute implements Route {
  private static final Logger LOG = Logger.getLogger(GetHomeRoute.class.getName());

  private static final Message WELCOME_MSG = Message.info("Welcome to the world of online Checkers.");

  static final String gameManagerKey = "test"; //CHANGE THIS, made this to make code work

  private final TemplateEngine templateEngine;

  private final GameManager gameManager;

  /**
   * Create the Spark Route (UI controller) to handle all {@code GET /} HTTP requests.
   *
   * @param templateEngine
   *   the HTML template rendering engine
   */
  public GetHomeRoute(final TemplateEngine templateEngine, final GameManager gameManager) {
    this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
    this.gameManager = gameManager;
    //
    LOG.config("GetHomeRoute is initialized.");
  }

  /**
   * Render the WebCheckers Home page.
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
    LOG.finer("GetHomeRoute is invoked.");

    Map<String, Object> vm = new HashMap<>();
    final Session httpSession = request.session();
    Player currentPlayer = httpSession.attribute("currentUser");  //get current player if there is one
    System.out.println(currentPlayer);

    //making message to show size of players
    int num = gameManager.returnLobby().sizeOfLobby();
    Message sizeOfLobbyMsg = Message.info("Current Number of Players: "+num);

    vm.put("title", "Welcome!");
    if(currentPlayer == null) {
      vm.put("message", WELCOME_MSG);
    }else{
      vm.put(PostSigninRoute.LIST_PLAYERS, gameManager.returnLobby().listOtherPlayers(currentPlayer.getName()));
      vm.put(PostSigninRoute.CURRENT_USER, gameManager.returnLobby().getPlayer(currentPlayer.getName()));
    }

    // display the number of plays currently logged in
    vm.put("num_of_players", sizeOfLobbyMsg);

    // render the View
    return templateEngine.render(new ModelAndView(vm , "home.ftl"));
  }
}
