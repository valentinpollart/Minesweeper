package user.network.handlers;

import game.Player;
import packets.PlayerId;
import user.Client;

public class PlayerIdHandler {
    private static PlayerIdHandler instance;

    public static PlayerIdHandler getInstance() {
        if (instance == null) {
            instance = new PlayerIdHandler();
        }
        return instance;
    }

    public void handle(PlayerId packet) {
        Player player = Client.getInstance().getPlayer();
        player.setId(packet.getId());
        Client.getInstance().setPlayer(player);
    }
}
