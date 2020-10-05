package packets;

import game.Player;

public class PlayerJoin extends Packet{
    private final Player player;

    public PlayerJoin(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }
}
