package com.webcheckers.appl;

import com.webcheckers.model.Player;
import com.webcheckers.util.Message;

import java.util.*;

/**
 * This class represents the lobby of all players
 * currently playing throughout the whole site.
 * @author Michael Taylor
 * @author Huan Huynh
 */
public class PlayerLobby {
    private static Map<String, Player> playerList;

    //initialize the player lobby
    public PlayerLobby(){
        playerList = new HashMap<>();
    }

    /**
     * Attempt to add the player to the lobby,
     * assuming the name is valid and the player is
     * not in the lobby
     * @param name name of player to be added
     * @return a {@Link Message} describing the attempt
     * to add and if it was successful
     */
    public synchronized Message addPlayer(String name) {
        name = name.strip();
        if (playerInLobby(name)) {
            return Message.error("Player name is taken");
        } else if (name.equals("")) {
            return Message.error("Enter a name");
        } else {
            int specialCharCount = 0;
            int letterCount = 0;
            int characterCount = 0;
            char[] tempName = name.toCharArray();
            for (char c : tempName) {
                if (!(Character.isSpaceChar(c)
                        || Character.isAlphabetic(c)
                        || Character.isDigit(c))) {
                    specialCharCount++;
                } else {
                    characterCount += 1;
                }
            }
            if (characterCount == 0) {
                return Message.error("Need at least one Alphanumeric character");
            }
            if (specialCharCount > 1) {
                return Message.error("Invalid player name");
            }
        }
        playerList.put(name, new Player(name));
        return new Message("You have been added to the lobby! Welcome " + name, Message.Type.INFO);
    }


    /**
     * Gets names of all other players except the player
     * you are signed in as
     * @param current_player player that is signed in (you)
     * @return set of all other players signed in
     */
    public Set<String> listOtherPlayers(String current_player) {
        //make a new set, copy the current set, and remove the current player
        Set<String> other_players = new HashSet<>(listOfNames());
        other_players.remove(current_player);
        return other_players;
    }

    /**
     * Gets number of people currently signed in
     * @return number of people in lobby
     */
    public int sizeOfLobby() { return playerList.size(); }

    /**
     * Gets names of all people signed in
     * @return set of names of people signed in
     */
    public Set<String> listOfNames() { return playerList.keySet(); }

    /**
     * Get all {@link Player}s currently in lobby
     * @return collection of Players in lobby
     */
    public Collection<Player> listOfPlayers() { return playerList.values(); }

    /**
     * Checks if player is signed in
     * @param name name of player to check
     * @return if the player is in the lobby
     */
    public boolean playerInLobby(String name) { return playerList.containsKey(name); }

    /**
     * Gets player in lobby
     * @param name name of player in lobby
     * @return the Player object in the lobby
     */
    public Player getPlayer(String name){ return playerList.get(name); }

    public void popPlayer(String player){ playerList.remove(player);}
}
