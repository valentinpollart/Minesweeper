package server.handlers;

import packets.PlayerLeft;
import server.ServerGame;
import server.ServerIOThread;
import server.ServerSocket;

public class PlayerLeftHandler {
    private static PlayerLeftHandler instance;

    public static PlayerLeftHandler getInstance() {
        if(instance == null) {
            instance = new PlayerLeftHandler();
        }
        return instance;
    }

    public void handle(PlayerLeft packet, ServerIOThread thread) {
        Integer gameId = packet.getPlayer().getGameId();
        if(gameId != null) {
            ServerGame game = ServerSocket.getInstance().getServerGame(gameId);
        }
        ServerSocket.getInstance().broadcast(packet);
    }
}
