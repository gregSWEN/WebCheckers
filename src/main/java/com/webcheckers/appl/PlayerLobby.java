package com.webcheckers.appl;

import com.webcheckers.model.Player;
import com.webcheckers.util.Message;

import java.util.*;

public class PlayerLobby {
    /** List of players throughout the site. Key is the player name*/
    private static Map<String, Player> playerList;

    //initialize the player lobby
    public PlayerLobby(){
        playerList = new HashMap<>();
    }

    /**
     * Attempts to add a player to the lobby, assuming the
     * name is valid and the player is not in the lobby
     * @param name name of player to be added
     * @return a Message describing whether the addition was a
     * success or failure
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
     * Gets number of current players in the lobby
     * @return number of players in lobby
     */
    public int sizeOfLobby() { return playerList.size(); }

    /**
     * Gets the names of the players in the lobby
     * @return a set of the names in the lobby
     */
    public Set<String> listOfNames() { return playerList.keySet(); }

    /**
     * Gets the Players that are in the lobby
     * @return a collection of PLayers that are in the game
     */
    public Collection<Player> listOfPlayers() { return playerList.values(); }

    /**
     * Checks whether a player is in the lobby
     * @param name name of player
     * @return if the player is in the lobby
     */
    public boolean playerInLobby(String name) { return playerList.containsKey(name); }
}
