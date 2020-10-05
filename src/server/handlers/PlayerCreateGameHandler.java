package server.handlers;

import packets.PlayerCreateGame;
import packets.WaitingRoom;
import server.ServerGame;
import server.ServerIOThread;
import server.ServerSocket;

public class PlayerCreateGameHandler {
    private static PlayerCreateGameHandler instance;

    public static PlayerCreateGameHandler getInstance() {
        if (instance == null) {
            instance = new PlayerCreateGameHandler();
        }
        return instance;
    }

    public void handle(PlayerCreateGame packet, ServerIOThread thread) {
        ServerGame game = new ServerGame(thread, packet.getDifficulty());
        ServerSocket.getInstance().addGame(game);
    }
}
