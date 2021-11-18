package com.webcheckers.appl;

import com.webcheckers.model.*;

import java.util.*;
import java.util.logging.Logger;

public class GameManager {
    private static final Logger LOG = Logger.getLogger(GameManager.class.getName());
    private PlayerLobby lobby = null;
    private Map<Integer, GameModel> games = new HashMap<>();

    public final static String GAME_PLAY_MESSAGE_YOU =
            "it's your turn. Move your piece and click the submit link." +
                    "If you want to erase your move click the reset link";
    public final static String GAME_PLAY_MESSAGE_OTHER =
            "it's %s turn. The page will refresh periodically" +
                    "and you will be informed when it is your turn";

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

    public static BoardView make_board(){
        List<Row> rows = new ArrayList<>();
        int length = 8;
        List<Integer> numbers = new ArrayList<>(Arrays.asList(0, 1, 0, 1, 0, 1, 0, 1));
        for(int i = 0; i < length; i++){
            List<Space> spaces = new ArrayList<>();
            int swit = numbers.get(i);
            for(int k = 0; k < length; k++){
                if(swit == 0 ){
                    Space space = new Space(k, Space.SpaceColor.WHITE, null);
                    swit = 1;
                    spaces.add(space);
                }
                else if(swit == 1){
                    if(i < 3){
                        Space space = new Space(k, Space.SpaceColor.BLACK, new Piece(Piece.Type.SINGLE, Piece.Color.RED));
                        swit = 0;
                        spaces.add(space);
                    }
                    else if(i > 4){
                        Space space = new Space(k, Space.SpaceColor.BLACK, new Piece(Piece.Type.SINGLE, Piece.Color.WHITE));
                        swit = 0;
                        spaces.add(space);
                    }
                    else{
                        Space space = new Space(k, Space.SpaceColor.BLACK, null);
                        swit = 0;
                        spaces.add(space);
                    }
                }
            }
            Row row = new Row(i, spaces);
            rows.add(row);
        }
        return new BoardView(rows);
    }

    public void addGame(GameModel game) {
        games.put(games.size(), game);
    }

    public int howManyGames(){ return games.size(); }

    public GameModel getGame(int id) {
        return games.get(id);
    }

    public Set<GameModel> list_all_games(){
        return new HashSet<>(games.values());
    }
}
