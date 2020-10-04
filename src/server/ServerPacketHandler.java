package server;

import game.Tile;
import packets.*;
import server.handlers.ChosenGameHandler;
import server.handlers.PlayerLeftHandler;
import server.handlers.PlayerLoginHandler;
import server.handlers.TileRequestHandler;

public class ServerPacketHandler {
    private static ServerPacketHandler instance;

    public static ServerPacketHandler getInstance() {
        if(instance == null) {
            instance = new ServerPacketHandler();
        }
        return instance;
    }

    public void handle(Packet packet, ServerIOThread thread){
        if(packet instanceof TileRequest) {
            TileRequestHandler.getInstance().handle((TileRequest) packet, thread);
        } else if (packet instanceof PlayerLogin) {
            PlayerLoginHandler.getInstance().handle((PlayerLogin) packet, thread);
        } else if(packet instanceof ChosenGame) {
            ChosenGameHandler.getInstance().handle((ChosenGame) packet, thread);
        } else if(packet instanceof PlayerLeft) {
            PlayerLeftHandler.getInstance().handle((PlayerLeft) packet, thread);
        }
    }

}
