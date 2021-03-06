package com.webcheckers.appl;

import static org.junit.jupiter.api.Assertions.*;

import com.webcheckers.model.Player;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * Tests the functionality of the PlayerLobby class, which
 * keeps track of players currently signed in
 */
@Tag("Application-tier")
public class PlayerLobbyTest {
    private PlayerLobby playerLobby = new PlayerLobby();
    private static final String NAME_TAKEN = "Player name is taken";
    private static final String BLANK_NAME = "Enter a name";
    private static final String INVALID_NAME = "Invalid player name";
    private static final String NO_ALPHANUM = "Need at least one Alphanumeric character";
    private static final String SUCCESS = "You have been added to the lobby! Welcome ";

    @Test
    public void testEmpty() {
        assertNotNull(playerLobby.listOfPlayers());
        assertNotNull(playerLobby.listOfNames());
        assertEquals(playerLobby.sizeOfLobby(), 0);
    }

    @Test
    public void testInvalidNames() {
        assertEquals(BLANK_NAME, playerLobby.addPlayer("     ").getText());
        assertEquals(NO_ALPHANUM, playerLobby.addPlayer("!!").getText());
        assertEquals(INVALID_NAME, playerLobby.addPlayer("acb!@#").getText());
    }

    @Test
    public void testPartFull() {
        playerLobby.addPlayer("Michael");
        assertEquals(NAME_TAKEN, playerLobby.addPlayer("Michael").getText());
        assertTrue(playerLobby.playerInLobby("Michael"));
        assertNotNull(playerLobby.listOfNames());
        assertEquals(1, playerLobby.sizeOfLobby());
        playerLobby.addPlayer("Bob");
        assertEquals(NAME_TAKEN, playerLobby.addPlayer("Bob").getText());
        assertTrue(playerLobby.playerInLobby("Bob"));
        assertNotNull(playerLobby.listOfNames());
        assertEquals(2, playerLobby.sizeOfLobby());
    }
}
