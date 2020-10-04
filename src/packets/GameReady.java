package packets;

public class GameReady extends Packet{
    private int gameId;

    public GameReady(int gameId) {
        this.gameId = gameId;
    }

    public int getGameId() {
        return gameId;
    }
}
