package server;

import game.MineField;
import game.Player;
import packets.Packet;

import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Vector;

public class ServerSocket {
    private static ServerSocket instance;
    private Vector<ServerIOThread> serverThreads = new Vector<ServerIOThread>();
    private Vector<ServerGame> serverGames = new Vector<ServerGame>();

    public static ServerSocket getInstance() {
        if(instance == null) {
            instance = new ServerSocket();
        }
        return instance;
    }

    public void start() throws IOException {
        java.net.ServerSocket serverSocket = new java.net.ServerSocket(Server.PORT);

        Socket clientSocket;
        ServerIOThread serverThread;

        try {
            clientSocket = serverSocket.accept();
            serverThread = new ServerIOThread(clientSocket);
            serverThreads.add(serverThread);

        } catch (IOException e) {

        }
    }

    public Vector<ServerGame> getServerGames() {
        return serverGames;
    }

    public ServerGame getServerGame(int gameId) {
        return serverGames.get(gameId);
    }

    public int getGameId(ServerGame game) {
        return serverGames.indexOf(game);
    }

    public Vector<HashMap<Player, MineField.Difficulty>> getGamesPlayers() {
        Vector<HashMap<Player, MineField.Difficulty>> gamesPlayers = new Vector<HashMap<Player, MineField.Difficulty>>();
        serverGames.forEach((K) -> gamesPlayers.add(K.getPlayers()));
        return gamesPlayers;
    }

    public void addGame(ServerGame game) {
        serverGames.add(game);
    }

    public void removeGame(int gameId) {
        serverGames.remove(gameId);
    }

    public void broadcast(Packet packet) {
        serverThreads.forEach(thread -> thread.send(packet));
    }
}