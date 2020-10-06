package server.handlers;

import game.MineField;
import game.Player;
import packets.GameReady;
import packets.PlayerReady;
import server.ServerGame;
import server.ServerIOThread;
import server.ServerSocket;

import java.util.HashMap;
import java.util.Vector;

public class PlayerReadyHandler {
    private static PlayerReadyHandler instance;
    private boolean isReady;

    public static PlayerReadyHandler getInstance() {
        if (instance == null) {
            instance = new PlayerReadyHandler();
        }
        return instance;
    }

    public void handle(PlayerReady packet, ServerIOThread thread) {
        isReady = true;
        Player player = packet.getPlayer();
        player.setReady(true);
        thread.setPlayer(player);
        ServerGame game = ServerSocket.getInstance().getServerGame(player.getGameId());
        game.broadcast(new PlayerReady(player), thread);
        game.getPlayers().forEach(((p, difficulty) -> isReady = isReady && p.getReady()));
        game.setReady(isReady);
        if(game.getReady()) {
            game.setGame(MineField.Difficulty.EASY);
            HashMap<Player, Integer> playerList = new HashMap<>();
            game.getPlayers().keySet().forEach((p) -> {
                playerList.put(p,p.getScore());
            });
            game.broadcast(new GameReady(playerList ,MineField.Difficulty.EASY), null);
        }
    }
}
