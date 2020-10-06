package user.network.handlers;

import packets.PlayerReady;
import user.views.WaitingRoomView;

public class PlayerReadyHandler {
    private static PlayerReadyHandler instance;

    public static PlayerReadyHandler getInstance() {
        if (instance == null) {
            instance = new PlayerReadyHandler();
        }
        return instance;
    }

    public void handle(PlayerReady packet) {
        WaitingRoomView.getInstance().setReady(packet.getPlayer().getId());
    }
}
