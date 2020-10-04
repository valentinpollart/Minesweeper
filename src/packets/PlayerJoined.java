package packets;

import game.MineField;
import game.Player;

public class PlayerJoined extends Packet {
    private Player player;
    private MineField.Difficulty difficulty;

    public Player getPlayer() {
        return player;
    }

    public MineField.Difficulty getDifficulty() {
        return difficulty;
    }
}
