package com.webcheckers.appl;

import com.webcheckers.model.Player;
import com.webcheckers.util.Message;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PlayerLobby {
    private Map<String, Player> playerList;

    public PlayerLobby() {
        this.playerList = new HashMap<>();
    }
    public Message addPlayer(String name) {
        if (playerInLobby(name)) {
            return new Message("Player name is taken", Message.Type.ERROR);
        } else {
            int specialCharCount = 0;
            char[] tempName = name.toCharArray();
            for (char c: tempName) {
                if (!(Character.isSpaceChar(c)
                     || Character.isAlphabetic(c)
                     || Character.isDigit(c))) {
                    specialCharCount++;
                    if (specialCharCount > 1) {
                        return new Message("Invalid player name", Message.Type.ERROR);
                    }
                }
            }
            playerList.put(name, new Player(name));
            return new Message("Player was added to lobby", Message.Type.INFO);
        }
    }


    private boolean playerInLobby(String name) {
        return playerList.containsKey(name);
    }
}
