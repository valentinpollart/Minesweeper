package packets;

import game.MineField;
import game.Player;

import java.util.HashMap;
import java.util.Vector;

public class GameList extends Packet {
    private Vector<HashMap<Player, MineField.Difficulty>> gameList = new Vector<HashMap<Player, MineField.Difficulty>>();

    public GameList(Vector<HashMap<Player, MineField.Difficulty>> gameList) {
        this.gameList = gameList;
    }

    public Vector<HashMap<Player, MineField.Difficulty>> getGameList() {
        return gameList;
    }
}
