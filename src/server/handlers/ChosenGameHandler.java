package server.handlers;

import packets.ChosenGame;
import packets.PlayerJoined;
import packets.WaitingRoom;
import server.ServerGame;
import server.ServerIOThread;
import server.ServerSocket;

public class ChosenGameHandler {
    private static ChosenGameHandler instance;

    public static ChosenGameHandler getInstance() {
        if(instance == null) {
            instance = new ChosenGameHandler();
        }
        return instance;
    }

    public void handle(ChosenGame packet, ServerIOThread thread) {
        ServerGame game = ServerSocket.getInstance().getServerGame(packet.getChosenGame());
        game.addPlayer(thread, packet.getDifficulty());
        ServerSocket.getInstance().broadcast(new PlayerJoined(thread.getPlayer(),packet.getDifficulty()),thread);
        thread.send(new WaitingRoom(game.getPlayers()));
    }
}
