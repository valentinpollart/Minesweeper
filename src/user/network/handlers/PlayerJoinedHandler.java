package user.network.handlers;

import packets.PlayerJoined;
import user.Client;
import user.views.WaitingRoomView;

public class PlayerJoinedHandler {
    private static PlayerJoinedHandler instance;

    public static PlayerJoinedHandler getInstance() {
        if (instance == null) {
            instance = new PlayerJoinedHandler();
        }
        return instance;
    }

    public void handle(PlayerJoined packet) {
        WaitingRoomView.getInstance().addPlayer(packet.getPlayer(), packet.getDifficulty());
        Client.getInstance().revalidate();
    }
}
