package server.handlers;

import packets.GameCreated;
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
        Integer gameId = ServerSocket.getInstance().getAttributableGameId();
        thread.getPlayer().setGameId(gameId);
        ServerGame game = new ServerGame(thread, packet.getDifficulty());
        game.setId(gameId);
        ServerSocket.getInstance().addGame(game);
        thread.send(new GameCreated(gameId));
    }
}
