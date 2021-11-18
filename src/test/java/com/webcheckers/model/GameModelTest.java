package com.webcheckers.model;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("Model-tier")
public class GameModelTest {

    @Test
    public void ctor_withArg() {
        Player player1 = new Player("red");
        Player player2 = new Player("white");
        GameModel model = new GameModel(player1, player2, 0);

        assertEquals(player1, model.getRedPlayer());
    }
    @Test
    public void ctor_withArg2() {
        Player player1 = new Player("red");
        Player player2 = new Player("white");
        GameModel model = new GameModel(player1, player2, 0);

        assertEquals(player2, model.getWhitePlayer());
    }
}
