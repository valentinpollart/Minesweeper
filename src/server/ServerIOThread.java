package server;

import game.Player;
import packets.Packet;

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
                e.printStackTrace();
            }
        }
    }

    public void send(Packet packet) {
        try {
            output.writeObject(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
