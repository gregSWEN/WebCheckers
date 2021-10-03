package com.webcheckers.appl;

import com.webcheckers.model.Player;

import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

public class GameManager {
    private static final logger LOG = Logger.getLogger(GameManager.class.getName());
    private PlayerLobby lobby;

    public final static String GAME_PLAY_MESSAGE_YOU = "it\'s your turn. Move your piece and click the submit link. If you want to erase your move click the reset link";
    public final static String GAME_PLAY_MESSAGE_OTHER = "it\'s %s turn. The page will refresh periodically and you will be informed when it is your turn";

    public Collection<Player> players(){
        return lobby.listOfPlayers();
    }
}
