package com.webcheckers.appl;

import com.webcheckers.model.BoardView;
import com.webcheckers.model.Player;
import com.webcheckers.model.Row;
import com.webcheckers.model.Space;

import java.util.*;
import java.util.logging.Logger;

public class GameManager {
    private static final Logger LOG = Logger.getLogger(GameManager.class.getName());
    private PlayerLobby lobby = null;

    public final static String GAME_PLAY_MESSAGE_YOU = "it\'s your turn. Move your piece and click the submit link. If you want to erase your move click the reset link";
    public final static String GAME_PLAY_MESSAGE_OTHER = "it\'s %s turn. The page will refresh periodically and you will be informed when it is your turn";

    public Collection<Player> players(){
        return lobby.listOfPlayers();
    }

    //return PlayerLobby, if NULL then make a PlayerLobby and return
    public PlayerLobby returnLobby(){
        if(lobby == null){
            lobby = new PlayerLobby();
        }
        return lobby;
    }

    public BoardView make_board(){
        List<Row> rows = new ArrayList<>();
        int length = 8;
        for(int i = 0; i < length; i++){
            List<Space> spaces = new ArrayList<>();
            int swit = 0;
            for(int k = 0; k < length; k++){
                if(swit == 0){
                    Space space = new Space(k, Space.SpaceColor.BLACK, null);
                    swit = 1;
                    spaces.add(space);
                }
                else{
                    Space space = new Space(k, Space.SpaceColor.WHITE, null);
                    swit = 0;
                    spaces.add(space);
                }
            }
            Row row = new Row(i, spaces);
        }
        BoardView board = new BoardView(rows);
        return board;
    }
}
