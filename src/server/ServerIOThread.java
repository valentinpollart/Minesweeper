package server;

import game.Player;
import packets.Packet;
import packets.PlayerLeft;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerIOThread extends Thread {
    private ObjectInputStream input;
    private ObjectOutputStream output;
    private Player player;

    ServerIOThread(Socket clientSocket) throws IOException {
        input = new ObjectInputStream(clientSocket.getInputStream());
        output = new ObjectOutputStream((clientSocket.getOutputStream()));
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public void run() {
        while (!interrupted()) {
            try {
                Packet packet = (Packet) this.input.readObject();
                ServerPacketHandler.getInstance().handle(packet, this);
            } catch (IOException | ClassNotFoundException e) {
                ServerSocket.getInstance().getServerThreads().remove(this);

                ServerSocket.getInstance().getServerGames().removeIf(serverGame -> {
                    if(serverGame.getThreads().containsKey(this)) {
                        serverGame.broadcast(new PlayerLeft(getPlayer()));
                        return true;
                    }
                    return false;
                });
            }
        }
    }

    public void send(Packet packet) {
        System.out.println("Sending " + packet.getClass().getName());
        try {
            output.writeObject(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
