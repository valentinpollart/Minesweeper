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
    private Vector<Integer> attributablePlayerIds = new Vector<>();
    private Vector<Integer> attributableGameIds = new Vector<>();

    public static ServerSocket getInstance() {
        if(instance == null) {
            instance = new ServerSocket();
        }
        return instance;
    }

    public void start() throws IOException {
        java.net.ServerSocket serverSocket = new java.net.ServerSocket(Server.PORT);

        Socket clientSocket = null;
        ServerIOThread serverThread = null;

        while (true) {
            try {
                clientSocket = serverSocket.accept();
                serverThread = new ServerIOThread(clientSocket);
                serverThreads.add(serverThread);
                serverThread.start();
                System.out.println("Accepted client");

            } catch (IOException e) {
                if (clientSocket != null) {
                    clientSocket.close();
                }

                if (serverThread != null) {
                    serverThread.interrupt();
                    serverThreads.remove(serverThread);
                }
            }
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

    public Integer getAttributablePlayerId() {
        if (attributablePlayerIds.isEmpty()) {
            return getServerThreads().size() - 1;
        }
        return attributablePlayerIds.firstElement();
    }

    public Integer getAttributableGameId() {
        if (attributableGameIds.isEmpty()) {
            return getServerThreads().size() - 1;
        }
        return attributableGameIds.firstElement();
    }

    public HashMap<Integer, HashMap<Player, MineField.Difficulty>> getGamesPlayers() {
        HashMap<Integer, HashMap<Player, MineField.Difficulty>> gamesPlayers = new HashMap<>();
        serverGames.forEach((K) -> {
            if (!K.getReady()) {
                gamesPlayers.put(K.getId(), K.getPlayers());
            }
        });
        return gamesPlayers;
    }

    public void addGame(ServerGame game) {
        serverGames.add(game);
    }

    public void removeGame(int gameId) {
        serverGames.remove(gameId);
    }

    public void broadcast(Packet packet, ServerIOThread exceptedThread) {
        serverThreads.forEach(thread -> {
             if (thread != exceptedThread) {
                 thread.send(packet);
             }
        });
    }

    public Vector<ServerIOThread> getServerThreads() {
        return serverThreads;
    }
}
