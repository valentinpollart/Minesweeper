package server.handlers;

import game.Player;
import game.Tile;
import packets.GameFinished;
import packets.TileRequest;
import packets.TileReveal;
import server.ServerGame;
import server.ServerIOThread;
import server.ServerSocket;

import java.util.HashMap;

public class TileRequestHandler {
    private static TileRequestHandler instance;
    private HashMap<Player,Integer> scoreboard = new HashMap<>();

    public static TileRequestHandler getInstance() {
        if(instance == null){
            instance = new TileRequestHandler();
        }
        return instance;
    }

    public void handle(TileRequest packet, ServerIOThread thread){
        Player player = packet.getSweeper();
        ServerGame game = ServerSocket.getInstance().getServerGame(player.getGameId());
        Tile tile = game.getGame().getTile(packet.getX(), packet.getY());
        if(tile.isMined()) {
            player.setHasLost(true);
            thread.setPlayer(player);
            if(game.isGameFinished()) {
                game.getPlayers().forEach((p,d) -> scoreboard.put(p, p.getScore()));
                game.broadcast(new GameFinished(scoreboard));
                return;
            }
        } else {
            player.incrementScore();
        }
        game.broadcast(new TileReveal(player, packet.getX(), packet.getY(), tile));
    }
}
