package packets;

public class GameCreated extends Packet{
    private final Integer gameId;

    public GameCreated(Integer gameId) {
        this.gameId = gameId;
    }

    public Integer getGameId() {
        return gameId;
    }
}
