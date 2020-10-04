package user.network.handlers;

import packets.PlayerLeft;
import packets.WaitingRoom;
import user.views.WaitingRoomView;

public class PlayerLeftHandler {
    private static PlayerLeftHandler instance;

    public static PlayerLeftHandler getInstance() {
        if (instance == null) {
            instance = new PlayerLeftHandler();
        }
        return instance;
    }

    public void handle(PlayerLeft packet) {
        WaitingRoomView.getInstance().removePlayer(packet.getPlayer());
    }
}
