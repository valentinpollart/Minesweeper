package packets;

import game.MineField;
import game.Player;

import java.util.HashMap;
import java.util.Vector;

public class GameReady extends Packet{
    private HashMap<Player, Integer> playerList;
    private MineField.Difficulty difficulty;

    public GameReady(HashMap<Player, Integer> playerList, MineField.Difficulty difficulty) {
        this.playerList = playerList;
        this.difficulty = difficulty;
    }

    public HashMap<Player, Integer> getPlayerList() {
        return playerList;
    }

    public MineField.Difficulty getDifficulty() {
        return difficulty;
    }
}
