package com.webcheckers.appl;

import com.webcheckers.model.Player;
import com.webcheckers.util.Message;

import java.util.*;

/**
 * This class is responsible for holding all the players
 * that are signed in throughout the site.
 * @author Michael Taylor
 */
public class PlayerLobby {
    /** contains the players in the sitewide lobby. Key is player name */
    private static Map<String, Player> playerList;

    //initialize the player lobby
    public PlayerLobby(){
        playerList = new HashMap<>();
    }

    /**
     * Attempts to add a player to the lobby,
     * assuming the name is valid and the player isn't
     * in the lobby
     * @param name name of the player to be added
     * @return a Message describing the attempt to add
     * the player and if it was successful
     * @author Michael Taylor
     */
    public synchronized Message addPlayer(String name) {
        name = name.strip();
        if (playerInLobby(name)) {
            return Message.error("Player name is taken");
        } else if (name.equals("")) {
            return Message.error("Enter a name");
        } else {
            int specialCharCount = 0;
            char[] tempName = name.toCharArray();
            for (char c: tempName) {
                if (!(Character.isSpaceChar(c)
                     || Character.isAlphabetic(c)
                     || Character.isDigit(c))) {
                    specialCharCount++;
                    if (specialCharCount > 1) {
                        return Message.error("Invalid player name");
                    }
                }
            }
            playerList.put(name, new Player(name));
            return new Message("You have been added to the lobby! Welcome "+ name, Message.Type.INFO);
        }
    }

    /**
     * Gets every other player in the lobby except
     * the player you are signed in as
     * @param current_player player to exclude form list (you)
     * @return a set of the other players
     * @author Huan Hunyh
     */
    public Set<String> listOtherPlayers(String current_player) {
        //make a new set, copy the current set, and remove the current player
        Set<String> other_players = new HashSet<>(listOfNames());
        other_players.remove(current_player);
        return other_players;
    }

    /**
     * Gets the amount of people in the lobby
     * @return number of people in lobby
     */
    public int sizeOfLobby() { return playerList.size(); }

    /**
     * Gets the names of all people in the lobby
     * @return set of the names of players in the lobby
     */
    public Set<String> listOfNames() { return playerList.keySet(); }

    /**
     * Gets the {@link Player}s in the lobby
     * @return a collection of the Players in the lobby
     */
    public Collection<Player> listOfPlayers() { return playerList.values(); }

    /**
     * Checks to see if a player is in the lobby
     * @param name name of player
     * @return if the player is in the lobby
     */
    public boolean playerInLobby(String name) { return playerList.containsKey(name); }
}
