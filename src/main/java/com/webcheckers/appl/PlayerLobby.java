package com.webcheckers.appl;

import com.webcheckers.model.Player;
import com.webcheckers.util.Message;

import java.util.*;

public class PlayerLobby {
    private static Map<String, Player> playerList;

    //initialize the player lobby
    public PlayerLobby(){
        playerList = new HashMap<String, Player>();
    }

    public synchronized Message addPlayer(String name) {
        if (playerInLobby(name)) {
            return Message.error("Player name is taken");
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
            return new Message("Player was added to lobby. Welcome "+ name, Message.Type.INFO);
        }
    }

    public int sizeOfLobby() { return playerList.size(); }

    public Set<String> listOfNames() {
        return playerList.keySet();
    }

    public Collection<Player> listOfPlayers() {
        return playerList.values();
    }

    public boolean playerInLobby(String name) {
        return playerList.containsKey(name);
    }
}
