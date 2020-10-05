package user.network;

import packets.Packet;
import packets.PlayerLogin;
import user.Client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientSocket {
    private static ClientSocket instance;
    private Socket socket;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private ClientIOThread thread;


    public static ClientSocket getInstance() {
        if(instance == null) {
            instance = new ClientSocket();
        }
        return instance;
    }

    public void connect(String ip, int port) throws IOException {
        socket = new Socket(ip, port);

        output = new ObjectOutputStream(socket.getOutputStream());

        input = new ObjectInputStream(socket.getInputStream());

        thread = new ClientIOThread(output, input);
        thread.start();

        send(new PlayerLogin(Client.getInstance().getPlayer()));
    }

    public void send(Packet packet) {
        try {
            System.out.println("Sending " + packet.getClass().getName());
            output.writeObject(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
