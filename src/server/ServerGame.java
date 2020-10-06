package server;

import game.MineField;
import game.Player;
import packets.Packet;

import java.util.HashMap;

public class ServerGame {
    private boolean isReady = false;
    private boolean isFinished = false;
    private Integer id;
    private MineField game;
    private HashMap<ServerIOThread, MineField.Difficulty> threads = new HashMap<ServerIOThread, MineField.Difficulty>();

    public ServerGame(ServerIOThread thread, MineField.Difficulty difficulty) {
        this.threads.put(thread, difficulty);
    }

    public boolean getReady() {
        return this.isReady;
    }

    public void setReady(boolean ready) {
        isReady = ready;
    }

    public boolean getFinished() {
        return this.isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public HashMap<ServerIOThread, MineField.Difficulty> getThreads() {
        return threads;
    }

    public MineField getGame() {
        return game;
    }

    public void setGame(MineField.Difficulty difficulty) {
        this.game = new MineField(difficulty);
        this.game.placeMines();
        this.game.setUnminedTiles();
    }

    public HashMap<Player, MineField.Difficulty> getPlayers() {
        HashMap<Player, MineField.Difficulty> players = new HashMap<Player, MineField.Difficulty>();
        threads.forEach((K,V) -> players.put(K.getPlayer(),V));
        return players;
    }

    public void addPlayer(ServerIOThread thread, MineField.Difficulty difficulty) {
        threads.put(thread, difficulty);
    }

    public void removePlayer(ServerIOThread thread) {
        threads.remove(thread);
    }

    public void setPlayerDifficulty(ServerIOThread thread, MineField.Difficulty difficulty) {
        threads.replace(thread, difficulty);
    }

    public boolean allPlayersLost() {
        isFinished = true;
        getPlayers().forEach((player,difficulty) -> isFinished = isFinished && player.getHasLost());
        return isFinished;
    }

    public void broadcast(Packet packet, ServerIOThread exceptedThread) {
        threads.forEach((thread,difficulty) -> {
            if (thread != exceptedThread) {
                thread.send(packet);
            }
        });
    }
}
