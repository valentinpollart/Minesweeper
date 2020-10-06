package user.network.handlers;

import packets.GameCreated;
import user.Client;

public class GameCreatedHandler {
    private static GameCreatedHandler instance;

    public static GameCreatedHandler getInstance() {
        if (instance == null) {
            instance = new GameCreatedHandler();
        }
        return instance;
    }

    public void handle(GameCreated packet) {
        Client.getInstance().getPlayer().setGameId(packet.getGameId());
    }
}
