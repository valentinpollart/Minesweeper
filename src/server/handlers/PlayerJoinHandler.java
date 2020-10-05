package server.handlers;

import packets.PlayerJoin;
import server.ServerIOThread;
import server.ServerSocket;

public class PlayerJoinHandler {
    private static PlayerJoinHandler instance;

    public static PlayerJoinHandler getInstance() {
        if (instance == null) {
            instance = new PlayerJoinHandler();
        }
        return instance;
    }

    public void handle(PlayerJoin packet, ServerIOThread thread) {
        ServerSocket.getInstance().broadcast(packet, thread);
    }
}
