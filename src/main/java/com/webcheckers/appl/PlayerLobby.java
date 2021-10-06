package com.webcheckers.appl;

import com.webcheckers.model.Player;
import com.webcheckers.util.Message;

import java.util.*;

public class PlayerLobby {
    private static Map<String, Player> playerList;

    //initialize the player lobby
    public PlayerLobby(){
        playerList = new HashMap<>();
    }

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

    public Set<String> listOtherPlayers(String current_player) {
        //make a new set, copy the current set, and remove the current player
        Set<String> other_players = new HashSet<String>(listOfNames());
        other_players.remove(current_player);
        return other_players;
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

    public Player getPlayer(String name){
        //return a Player given String name
        Collection<Player> players = listOfPlayers();
            for(Player player: players){
                if(player.getName().equals(name)){
                    return player;
                }
            }
            return null;
    }
}
