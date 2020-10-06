package user.network;

import packets.*;
import user.network.handlers.*;

public class ClientPacketHandler {
    private static ClientPacketHandler instance;

    public static ClientPacketHandler getInstance() {
        if (instance == null) {
            instance = new ClientPacketHandler();
        }
        return instance;
    }

    public void handle(Packet packet) {
        System.out.println("Receiving " + packet.getClass().getName());
        if(packet instanceof TileReveal) {
            TileRevealHandler.getInstance().handle((TileReveal) packet);
        } else if (packet instanceof GameList) {
            GameListHandler.getInstance().handle((GameList) packet);
        } else if (packet instanceof WaitingRoom) {
            WaitingRoomHandler.getInstance().handle((WaitingRoom) packet);
        } else if (packet instanceof PlayerJoined) {
            PlayerJoinedHandler.getInstance().handle((PlayerJoined) packet);
        } else if (packet instanceof PlayerLeft) {
            PlayerLeftHandler.getInstance().handle((PlayerLeft) packet);
        } else  if (packet instanceof PlayerReady) {
            PlayerReadyHandler.getInstance().handle((PlayerReady) packet);
        } else if (packet instanceof  PlayerId) {
            PlayerIdHandler.getInstance().handle((PlayerId) packet);
        } else if (packet instanceof GameReady) {
            GameReadyHandler.getInstance().handle((GameReady) packet);
        } else if (packet instanceof  GameCreated) {
            GameCreatedHandler.getInstance().handle((GameCreated) packet);
        }
    }
}
