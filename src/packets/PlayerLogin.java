package packets;

import game.Player;

public class PlayerLogin extends Packet {
    private Player player;

    public PlayerLogin(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }
}
