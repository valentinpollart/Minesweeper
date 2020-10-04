package packets;

import game.Player;

public class PlayerLeft extends Packet {
    private final Player player;

    public PlayerLeft(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }
}
