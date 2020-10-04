package server.handlers;

import packets.GameList;
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
        thread.send(new GameList(ServerSocket.getInstance().getGamesPlayers()));
    }
}
