package packets;

import game.Player;

public class PlayerId extends Packet{
    private final Integer id;

    public PlayerId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
