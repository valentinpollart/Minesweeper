package user.network.handlers;

import game.MineField;
import packets.PlayerJoin;
import packets.PlayerJoined;
import packets.PlayerLogin;
import packets.WaitingRoom;
import user.Client;
import user.network.ClientSocket;
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
        WaitingRoomView.getInstance().setPlayerList(packet.getPlayerList());
        Client.getInstance().setContentPane(WaitingRoomView.getInstance());
        Client.getInstance().revalidate();
        ClientSocket.getInstance().send(new PlayerJoin(Client.getInstance().getPlayer()));
    }
}
