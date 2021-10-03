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
        }
        return null;
    }


    private boolean playerInLobby(Player player) {
        return playerList.contains(player);
    }
}
