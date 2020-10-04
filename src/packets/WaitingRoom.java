package packets;

import game.MineField;
import game.Player;

import java.util.HashMap;

public class WaitingRoom extends Packet {
    private HashMap<Player, MineField.Difficulty> playerList = new HashMap<Player, MineField.Difficulty>();

    public WaitingRoom(HashMap<Player, MineField.Difficulty> playerList) {
        this.playerList = playerList;
    }

    public HashMap<Player, MineField.Difficulty> getPlayerList() {
        return playerList;
    }
}
