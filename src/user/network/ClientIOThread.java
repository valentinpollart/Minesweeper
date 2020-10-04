package user.network;

import packets.Packet;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ClientIOThread extends Thread {
    private ObjectOutputStream output;
    private ObjectInputStream input;

    public ClientIOThread(ObjectOutputStream output, ObjectInputStream input) {
        this.output = output;
        this.input = input;
    }

    @Override
    public void run() {
        while (!interrupted()) {
            try {
                Packet packet = (Packet) input.readObject();
                ClientPacketHandler.getInstance().handle(packet);
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
