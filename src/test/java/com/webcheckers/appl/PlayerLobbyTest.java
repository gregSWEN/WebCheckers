package com.webcheckers.appl;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

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
        assertNull(playerLobby.listOfPlayers());
        assertNull(playerLobby.listOfNames());
        assertEquals(playerLobby.sizeOfLobby(), 0);
    }

    @Test
    public void testInvalidNames() {
        assertEquals(playerLobby.addPlayer("     ").getText(), BLANK_NAME);
        assertEquals(playerLobby.addPlayer("13344242").getText(), NO_ALPHANUM);
        assertEquals(playerLobby.addPlayer("acb!@#").getText(), INVALID_NAME);
    }

    @Test
    public void testPartFull() {
        playerLobby.addPlayer("Michael");
        assertEquals(playerLobby.addPlayer("Michael").getText(), NAME_TAKEN);
        assertTrue(playerLobby.playerInLobby("Michael"));
        assertNotNull(playerLobby.listOfNames());
        assertEquals(playerLobby.sizeOfLobby(), 1);
        playerLobby.addPlayer("Bob");
        assertEquals(playerLobby.addPlayer("Bob").getText(), NAME_TAKEN);
        assertTrue(playerLobby.playerInLobby("Bob"));
        assertNotNull(playerLobby.listOfNames());
        assertEquals(playerLobby.sizeOfLobby(), 1);
    }
}
