package user.network.handlers;

import packets.WaitingRoom;
import user.views.WaitingRoomView;

public class WaitingRoomHandler {
    private static WaitingRoomHandler instance;

    public static WaitingRoomHandler getInstance() {
        if (instance == null) {
            instance = new WaitingRoomHandler();
        }
        return instance;
    }

    public void handle(WaitingRoom packet) {
        WaitingRoomView.getInstance().redraw(packet.getPlayerList());
    }
}
