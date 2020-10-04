package packets;

import game.Player;

import java.util.HashMap;

public class GameFinished extends Packet {
    private HashMap<Player,Integer> scoreboard;

    public GameFinished(HashMap<Player,Integer> scoreboard) {
        this.scoreboard = scoreboard;
    }

    public HashMap<Player, Integer> getScoreboard() {
        return scoreboard;
    }
}
