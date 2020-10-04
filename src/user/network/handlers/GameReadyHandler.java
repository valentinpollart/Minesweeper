package user.network.handlers;

import packets.GameReady;
import user.Client;
import user.views.GameView;

public class GameReadyHandler {
    private static GameReadyHandler instance;

    public static GameReadyHandler getInstance() {
        if (instance == null) {
            instance = new GameReadyHandler();
        }
        return instance;
    }

    public void handle(GameReady packet) {
        GameView.getInstance().redraw();
        Client.getInstance().getPlayer().setGameId(packet.getGameId());
    }
}
