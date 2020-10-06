package packets;

import game.Player;

public class PlayerReady extends Packet{
    private final Player player;

    public PlayerReady(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }
}
