package server.handlers;

import game.Player;
import packets.GameReady;
import packets.PlayerReady;
import server.ServerGame;
import server.ServerIOThread;
import server.ServerSocket;

public class PlayerReadyHandler {
    private static PlayerReadyHandler instance;

    public static PlayerReadyHandler getInstance() {
        if (instance == null) {
            instance = new PlayerReadyHandler();
        }
        return instance;
    }

    public void handle(PlayerReady packet, ServerIOThread thread) {
        Player player = packet.getPlayer();
        thread.setPlayer(player);
        ServerGame game = ServerSocket.getInstance().getServerGame(player.getGameId());
        game.broadcast(new PlayerReady(player));
        game.getPlayers().forEach(((p, difficulty) -> game.setReady(game.getReady() & p.getReady())));
        if(game.getReady()) {
            game.broadcast(new GameReady(ServerSocket.getInstance().getGameId(game)));
        }
    }
}
