package server;

import packets.*;
import server.handlers.*;

public class ServerPacketHandler {
    private static ServerPacketHandler instance;

    public static ServerPacketHandler getInstance() {
        if(instance == null) {
            instance = new ServerPacketHandler();
        }
        return instance;
    }

    public void handle(Packet packet, ServerIOThread thread){
        System.out.println("Receiving " + packet.getClass().getName());
        if(packet instanceof TileRequest) {
            TileRequestHandler.getInstance().handle((TileRequest) packet, thread);
        } else if (packet instanceof PlayerLogin) {
            PlayerLoginHandler.getInstance().handle((PlayerLogin) packet, thread);
        } else if(packet instanceof ChosenGame) {
            ChosenGameHandler.getInstance().handle((ChosenGame) packet, thread);
        } else if(packet instanceof PlayerLeft) {
            PlayerLeftHandler.getInstance().handle((PlayerLeft) packet, thread);
        } else if (packet instanceof PlayerCreateGame) {
            PlayerCreateGameHandler.getInstance().handle((PlayerCreateGame) packet, thread);
        } else if (packet instanceof PlayerJoin) {
            PlayerJoinHandler.getInstance().handle((PlayerJoin) packet, thread);
        }
    }

}
