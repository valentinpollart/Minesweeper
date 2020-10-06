package server.handlers;

import game.Player;
import packets.GameList;
import packets.PlayerId;
import packets.PlayerLogin;
import server.ServerIOThread;
import server.ServerSocket;

public class PlayerLoginHandler {
    private static PlayerLoginHandler instance;

    public static PlayerLoginHandler getInstance() {
        if(instance == null){
            instance = new PlayerLoginHandler();
        }
        return instance;
    }

    public void handle(PlayerLogin packet, ServerIOThread thread){
        Player player = packet.getPlayer();
        Integer playerId = ServerSocket.getInstance().getAttributablePlayerId();
        player.setId(playerId);
        thread.setPlayer(player);
        thread.send(new PlayerId(playerId));
        thread.send(new GameList(ServerSocket.getInstance().getGamesPlayers()));
    }
}
