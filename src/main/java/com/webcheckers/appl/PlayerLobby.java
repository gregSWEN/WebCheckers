package com.webcheckers.appl;

import com.webcheckers.model.Player;
import com.webcheckers.util.Message;

import java.util.HashSet;
import java.util.Set;

public class PlayerLobby {
    private Set<Player> playerList;

    public PlayerLobby() {
        this.playerList = new HashSet<>();
    }
    public Message addPlayer(Player player) {
        if (playerInLobby(player)) {
            return new Message("Player name is taken", Message.Type.ERROR);
        } else {
            int specialCharCount = 0;
            char[] tempName = player.getName().toCharArray();
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
            playerList.add(player);
            return new Message("Player was added to lobby", Message.Type.INFO);
        }
    }


    private boolean playerInLobby(Player player) {
        return playerList.contains(player);
    }
}
