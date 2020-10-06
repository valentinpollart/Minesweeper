package packets;

import game.MineField;
import game.Player;

import java.util.HashMap;
import java.util.Vector;

public class GameList extends Packet {
    private final HashMap<Integer, HashMap<Player, MineField.Difficulty>> gameList;

    public GameList(HashMap<Integer, HashMap<Player, MineField.Difficulty>> gameList) {
        this.gameList = gameList;
    }

    public HashMap<Integer, HashMap<Player, MineField.Difficulty>> getGameList() {
        return gameList;
    }
}
