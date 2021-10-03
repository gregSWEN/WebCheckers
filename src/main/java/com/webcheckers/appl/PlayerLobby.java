package com.webcheckers.appl;

import com.webcheckers.model.Player;
import com.webcheckers.util.Message;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PlayerLobby {
    private static Map<String, Player> playerList;

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
            return new Message("Player was added to lobby", Message.Type.INFO);
        }
    }

    public int sizeOfLobby() { return playerList.size(); }

    private boolean playerInLobby(String name) {
        return playerList.containsKey(name);
    }
}
