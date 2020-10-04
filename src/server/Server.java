package server;

import java.io.IOException;

public class Server {
    public static final int PORT = 4269;

    public static void main(String[] args) throws IOException { ServerSocket.getInstance().start(); }
}
