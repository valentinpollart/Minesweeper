package packets;

import game.MineField;
import game.Player;

public class PlayerJoined extends Packet {
    private final Player player;
    private final MineField.Difficulty difficulty;

    public PlayerJoined(Player player, MineField.Difficulty difficulty) {
        this.player = player;
        this.difficulty = difficulty;
    }

    public Player getPlayer() {
        return player;
    }

    public MineField.Difficulty getDifficulty() {
        return difficulty;
    }
}
